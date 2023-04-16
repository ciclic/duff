import dotenv from 'dotenv';
import { expressAdapter } from './infra/http/server/expressAdapter';
import { MongoAdapter } from './infra/database/mongodb/mongoAdapter';
import { castStringToNumber } from './shared/helpers/helpers';

dotenv.config();
const PORT: number = castStringToNumber(process.env.PORT, 3000);

const server = expressAdapter();

const database = MongoAdapter();

(() => {
  try {
    database.connect();
  } finally {
    setTimeout(() => server.start(PORT), 1000);
  }
})();
