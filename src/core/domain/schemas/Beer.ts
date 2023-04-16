import mongoose from "mongoose";

const beerSchema = new mongoose.Schema({
  minTemperature: {
    type: Number,
    required: true
  },
  maxTemperature: {
    type: Number,
    required: true
  },
  style: {
    type: String,
    required: true
  }
});

export const Beer = mongoose.model('Beer', beerSchema);