import express from "express";
import createRouteDecorator from "./createRoute";

import dotenv from 'dotenv';
import { findSuitablePlaylist } from "../../../core/services/findSuitablePlaylist";
dotenv.config();


const router = express.Router();
const Controller = createRouteDecorator;

Controller('get', '/playlist', router, async (req, res) => {
  const playlist = await findSuitablePlaylist();
  res.send(playlist);
});

export default router;