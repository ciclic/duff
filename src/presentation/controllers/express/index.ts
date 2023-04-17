import express, { Request, Response } from 'express';

import { Beer } from '../../../core/domain/schemas/Beer';
import { findSuitableBeer } from '../../../core/services/findSuitableBeer';
import createRouteDecorator from './createRoute';
import { createBeer } from '../../../core/services/createBeer';
import { initDatabase } from '../../../core/services/initDatabase';

const router = express.Router();
const Controller = createRouteDecorator;

Controller('get', '/health', router, async (req: Request, res: Response) => {
  res.send('Service available');
});

Controller('get', '/suitable', router, async (req: Request, res: Response) => {
  const { temperature = 0 } = req.query;
  const suitableBeer = await findSuitableBeer(Number(temperature));
  return res.status(200).json(suitableBeer);
});

Controller('get', '/', router, async (req: Request, res: Response) => {
  const {
    style, minTemperature, maxTemperature, skip = 0, limit = 9999,
  } = req.query;

  const filter = {
    ...(style && { style }),
    ...(minTemperature && { minTemperature }),
    ...(maxTemperature && { maxTemperature }),
  };

  const beers = await Beer.find(filter).skip(Number(skip)).limit(Number(limit));
  return res.status(200).json(beers);
});

Controller('post', '/', router, async (req: Request, res: Response) => {
  const { style, minTemperature, maxTemperature } = req.body;
  await createBeer({ style, minTemperature, maxTemperature });
  return res.status(201).json({ message: 'CREATED' });
});

Controller('put', '/', router, async (req: Request, res: Response) => {
  const { style, minTemperature, maxTemperature } = req.body;

  const beer = {
    minTemperature,
    maxTemperature,
    style,
  };

  const foundBeer = await Beer.findOne({ style: 'Dunkel' });

  let result;
  if (foundBeer) {
    result = await Beer.updateOne(beer);
  }

  return res.status(200).json(result);
});

Controller('delete', '/', router, async (req: Request, res: Response) => {
  const { style } = req.body;
  // add validation
  if (!style) {
    return res.status(400).json({
      error: 'Bad request',
    });
  }

  await Beer.deleteOne({ style });
  return res.status(204).send();
});

Controller('post', '/init', router, async (req: Request, res: Response) => {
  await initDatabase();
  return res.status(204).send();
});

export default router;
