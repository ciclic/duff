import dotenv from 'dotenv';
import { expressAdapter } from './ports_and_adapters/server/expressAdapter';

dotenv.config();
const { PORT = 3000 } = process.env; 

expressAdapter().start(PORT);