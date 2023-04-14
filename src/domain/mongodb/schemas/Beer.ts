import mongoose from "mongoose";

const beerSchema = new mongoose.Schema({
  temperature: {
    type: Number,
    required: true
  },
  style: {
    type: String,
    required: true
  }
});

const Beer = mongoose.model('Beer', beerSchema);

module.exports = Beer;