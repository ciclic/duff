import dotenv from 'dotenv';
import { expressAdapter } from './ports_and_adapters/server/expressAdapter';
import { castStringToNumber } from './helpers/helpers';
import { MongoAdapter } from './ports_and_adapters/database/mongoAdapter';

dotenv.config();
const PORT: number = castStringToNumber(process.env.PORT, 3000)

const server = expressAdapter();

const database = MongoAdapter();


(() => {
    try {
        database.connect();
    } finally {
        setTimeout(() => server.start(PORT), 1000);
    }
}) ();

