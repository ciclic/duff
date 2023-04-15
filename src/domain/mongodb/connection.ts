import mongoose from "mongoose";
import dotenv from 'dotenv';
import { DatabasePort } from "../../ports_and_adapters/database/databasePort";
dotenv.config();

const { DB_USER, DB_PASS, DB_HOST, DB_PORT, DB_NAME } = process.env;

export const MongoAdapter: DatabasePort = () => {
  const connect = () => {
    const uri = `mongodb://${DB_USER}:${DB_PASS}@${DB_HOST}:${DB_PORT}/${DB_NAME}`;

    console.log("Trying to connect to database...");
    mongoose
      .connect(uri)
      .catch(error => console.log(error));
    mongoose.connection.on("error", () => console.log('connection error:'));
    mongoose.connection.once("open", () => console.log('database connected'));
  }

  return {
    connect
  }
}