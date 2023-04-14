import express from 'express';
import bodyParser from 'body-parser';
import { ServerPort } from './serverPort';

export const expressAdapter: ServerPort = () => {
    const start = (port: number) => {
        const app = express();
        app.use(express.json());
        app.use(bodyParser.json());

        app.get('/test', (req, res) => {
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

