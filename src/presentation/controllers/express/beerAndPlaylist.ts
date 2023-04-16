import express from "express";
import createRouteDecorator from "./createRoute";

import dotenv from 'dotenv';
import { findSuitablePlaylist } from "../../../core/services/findSuitablePlaylist";
import { findSuitableBeer } from "../../../core/services/findSuitableBeer";
dotenv.config();


const router = express.Router();
const Controller = createRouteDecorator;

Controller('get', '/beer-playlist', router, async (req, res) => {
  const { temperature } = req.query;
  const suitableBeer = await findSuitableBeer(Number(temperature));
  console.log(suitableBeer[0].style)
  const { style } = suitableBeer[0];
  const suitablePlaylist = await findSuitablePlaylist(String(style));

  const response = {
    beerStyle: style,
    playlist: {	
        name: suitablePlaylist.body.name,
        tracks: suitablePlaylist?.body?.tracks?.items.map(item => ({
            name: item.track?.name,
            artist: item.track?.artists[0].name,
            link: item.track?.external_urls.spotify
        }))
    }
  }

  res.status(200).json(response);
});

export default router;