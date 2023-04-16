import express from "express";
import createRouteDecorator from "./createRoute";

import dotenv from 'dotenv';
import { findSuitablePlaylist } from "../../../core/services/findSuitablePlaylist";
dotenv.config();


const router = express.Router();
const Controller = createRouteDecorator;

Controller('get', '/playlist', router, async (req, res) => {
  const { style } = req.query;
  const playlist = await findSuitablePlaylist(String(style));
  res.send(playlist);
});

export default router;