import express from 'express';
import bodyParser from 'body-parser';
import { ServerPort } from './serverPort';
import { timeLog } from '../../middlewares/time-log';
import router from '../../routes/express/routes';

export const expressAdapter: ServerPort = () => {
    const start = (port: number) => {
        const app = express();
        app.use(express.json());
        app.use(bodyParser.json());

        app.use(timeLog)
        app.use(router)

        router.get('/', (req, res) => {
            res.send('Hello World!');
        });

        app.listen(port, () => {
            console.log(`Server is running on port ${port}`);
        });
    };

    return {
      start
    };  
}

