import dotenv from 'dotenv';
import { expressAdapter } from './ports_and_adapters/server/expressAdapter';
import { castStringToNumber } from './helpers/helpers';
import { connect } from './domain/mongodb/connection';

dotenv.config();
const PORT: number = castStringToNumber(process.env.PORT, 3000)

const server = expressAdapter();
console.log(PORT);

(() => {
    server.start(PORT);
    connect()
}) ();

