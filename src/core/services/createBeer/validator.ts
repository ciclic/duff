import { BadRequest } from "../../../shared/customErrors/badRequest";
import { BeerInput } from "./type";

export const validateBeerInput = ({ style, minTemperature, maxTemperature }: BeerInput) => {
  if (!style && !minTemperature && !maxTemperature) {
    throw new BadRequest('Bad request');
  }

  if (maxTemperature < minTemperature) {
    throw new BadRequest('Max temperature must be greater than min temperature');
  }
}