import dotenv from 'dotenv';
import { expressAdapter } from './ports_and_adapters/server/expressAdapter';
import { castStringToNumber } from './helpers/helpers';
import { MongoAdapter } from './domain/mongodb/connection';

dotenv.config();
const PORT: number = castStringToNumber(process.env.PORT, 3000)

const server = expressAdapter();

const database = MongoAdapter();

(() => {
    server.start(PORT);
    database.connect()
}) ();

