import { Beer } from "../../domain/schemas/Beer";

export const findSuitableBeer = async (temperature: number) => {
    const suitableBeer = await Beer.aggregate([
        {
            $project: {
                style: '$style',
                avg_temperature: { $avg: [{$toDouble: '$minTemperature'}, {$toDouble: '$maxTemperature'}] } 
            }
        },
        {
            $addFields: {
                distance: { $abs: { $subtract: [{$toDouble: temperature}, '$avg_temperature'] } } 
            }
        },
        {
            $sort: { distance: 1 }
        },
        {
            $limit: 1
        }
    ]); 

    return suitableBeer;
}