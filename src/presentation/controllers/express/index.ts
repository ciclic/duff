import express from "express";
import { Router, Request, Response } from 'express';
import { Beer } from "../../../core/domain/schemas/Beer";
import { findSuitableBeer } from "../../../core/services/findSuitableBeer";
import createRouteDecorator from "./createRoute";

const router = express.Router();
const Controller = createRouteDecorator;


Controller('get', '/health', router,  async (req, res) => {
    res.send('Service available');
});

Controller('get', '/suitable', router, async(req, res) => {
    const { temperature = 0 } = req.query;
    const suitableBeer = await findSuitableBeer(Number(temperature));
    return res.status(200).json(suitableBeer);
});

Controller('get', '/', router, async (req, res) => {
    const { style, minTemperature, maxTemperature, skip = 0, limit = 9999 } = req.query;

    const filter = {
        ...( style && { style }),
        ...( minTemperature && { minTemperature }),
        ...( maxTemperature && { maxTemperature })
    };

    const beers = await Beer.find(filter).skip(Number(skip)).limit(Number(limit));
    return res.status(200).json(beers)
});

Controller('post', '/', router, async (req, res) => {
    const {style, minTemperature, maxTemperature } = req.body;
    if (!style || !minTemperature || !maxTemperature) {
        return res.status(400).json({
            error: 'Bad request'
        });
    }

    const beer = {
        minTemperature,
        maxTemperature,
        style
    } 
      
    const foundBeer = await Beer.findOne({ style });
    if(foundBeer) {
        return res.status(401).json({ message: "This beer style already exists on database" })
    }

    await Beer.create(beer);
    return res.status(201).json({ message: "CREATED" });
});

Controller('put', '/', router, async(req, res) => {
    const {style, minTemperature, maxTemperature } = req.body;

    const beer = {
        minTemperature,
        maxTemperature,
        style
    } 

    const foundBeer = await Beer.findOne({style: "Dunkel"});

    let result;
    if(foundBeer) {
        result = await Beer.updateOne(beer);
    }

    return res.status(200).json(result);
});

Controller('delete', '/', router, async (req, res) => {
    const { style } = req.body;
    // add validation
    if (!style) {
        return res.status(400).json({
            error: 'Bad request'
        });
    }

    await Beer.deleteOne({ style });
    return res.status(204).send();
});

export default router;