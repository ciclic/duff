import express from "express";
const router = express.Router();

import { Beer } from "../../domain/mongodb/schemas/Beer";

router.get('/test', (req, res) => {
    res.send('Hello World!');
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
        return res.status(500).send({ error });
    }
});

router.put('/', async (req, res) => {
    const beer = {
        minTemperature: 0,
        maxTemperature: 1,
        style: "Dunkel"
    } 

    try {
        const foundBeer = await Beer.findOne({style: "Dunkel"});

        let result;
        if(foundBeer) {
            result = await Beer.updateOne(beer);
        }

        return res.status(200).json(result);
    } catch(error) {
        return res.status(500).send({ error });
    }
})

export default router;