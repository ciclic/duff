import { Beer } from '../../domain/schemas/Beer';

const cache = new Map();

export const findSuitableBeer = async (temperature: number) => {
  if (cache.has(temperature)) {
    return cache.get(temperature);
  }

  const mostSuitableBeer = await Beer.aggregate([
    {
      $project: {
        style: '$style',
        avg_temperature: { $avg: [{ $toDouble: '$minTemperature' }, { $toDouble: '$maxTemperature' }] },
      },
    },
    {
      $addFields: {
        distance: { $abs: { $subtract: [{ $toDouble: temperature }, '$avg_temperature'] } },
      },
    },
    {
      $sort: { distance: 1, style: 1 },
    },
    {
      $limit: 1,
    },
  ]);

  cache.set(temperature, mostSuitableBeer);

  return mostSuitableBeer;
};
