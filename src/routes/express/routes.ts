import express from "express";
const router = express.Router();

import { Beer } from "../../domain/mongodb/schemas/Beer";

router.get('/health', (req, res) => {
    res.send('Service available');
});

router.get('/', async (req, res) => {
    const { style, minTemperature, maxTemperature, skip = 0, limit = 9999 } = req.query;

    const filter = {
        ...( style && { style }),
        ...( minTemperature && { minTemperature }),
        ...( maxTemperature && { maxTemperature })
    };

    console.log(filter, skip, limit)

    try {
        const beers = await Beer.find(filter).skip(Number(skip)).limit(Number(limit));
        return res.status(200).json(beers)
    } catch(error) {
        return res.status(500).json({ error });
    }
});

router.post('/', async (req, res) => {
    const {style, minTemperature, maxTemperature } = req.body;

    const beer = {
        minTemperature,
        maxTemperature,
        style
    } 
    
    try {   
        const foundBeer = await Beer.findOne({ style });
        if(foundBeer) {
            return res.status(401).json({ message: "This beer style already exists on database" })
        }

        await Beer.create(beer);
        return res.status(201).json({ message: "CREATED" });
    } catch(error) {
        return res.status(500).json({ error });
    }
});

router.put('/', async (req, res) => {
    const {style, minTemperature, maxTemperature } = req.body;

    const beer = {
        minTemperature,
        maxTemperature,
        style
    } 

    try {
        const foundBeer = await Beer.findOne({style: "Dunkel"});

        let result;
        if(foundBeer) {
            result = await Beer.updateOne(beer);
        }

        return res.status(200).json(result);
    } catch(error) {
        return res.status(500).json({ error });
    }
});

router.delete('/', async (req, res) => {
    const { style } = req.body;

    try {
        await Beer.deleteOne({ style });
        return res.status(204).send()
    } catch(error) {
        return res.status(500).json({ error });
    }
});

export default router;