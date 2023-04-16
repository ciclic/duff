import express from 'express';
import bodyParser from 'body-parser';
import { ServerPort } from './ports/serverPort';
import { timeLog } from '../../../core/middlewares/time-log';
import router from '../../../presentation/controllers/express';
import spotifyRouter from '../../../presentation/controllers/express/spotify';
import beerPlaylistRouter from '../../../presentation/controllers/express/beerAndPlaylist';


export const expressAdapter: ServerPort = () => {
    const start = (port: number) => {
        const app = express();
        app.use(express.json());
        app.use(bodyParser.json());

        app.use(timeLog)
        app.use(router)
        app.use(spotifyRouter)
        app.use(beerPlaylistRouter)

        app.listen(port, () => {
            console.log(`Server is running on port ${port}`);
        });
    };

    return {
      start
    };  
}

