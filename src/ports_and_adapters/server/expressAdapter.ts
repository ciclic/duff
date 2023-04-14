import express from 'express';
import { ServerPort } from "./serverPort";

export const expressAdapter: ServerPort = () => {
    const start = (port: number) => {
        const app = express();
        app.use(express.json());

        app.listen(port, () => {
            console.log(`Server is running on port ${port}`);
        });
    };

    return {
      start,
    };  
}

