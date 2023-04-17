import mongoose from 'mongoose';
import dotenv from 'dotenv';
import { DatabasePort } from '../ports/databasePort';

dotenv.config();

const {
  DB_USER, DB_PASS, DB_HOST, DB_PORT, DB_NAME,
} = process.env;

export const MongoAdapter: DatabasePort = () => {
  const connect = () => {
    const uri = `mongodb://${DB_USER}:${DB_PASS}@${DB_HOST}:${DB_PORT}`;

    mongoose
      .connect(uri, { dbName: DB_NAME })
      .catch((error) => console.log(error));
    mongoose.connection.on('error', () => console.log('connection error:'));
    mongoose.connection.once('open', () => console.log('Database connected'));
  };

  return {
    connect,
  };
};
