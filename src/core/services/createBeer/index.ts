import { Beer } from "../../domain/schemas/Beer";
import { BeerInput } from "./type";
import { validateBeerInput } from "./validator";

type Beer = { style: string, minTemperature: number, maxTemperature: number }

export const createBeer = async({ style, minTemperature, maxTemperature }: BeerInput) => {
  const beer = {
    minTemperature,
    maxTemperature,
    style,
  };

  validateBeerInput(beer);

  const foundBeer = await Beer.findOne({ style });
  if (foundBeer) {
    throw Error('This beer style already exists on database');
  }

  return Beer.create(beer);
}