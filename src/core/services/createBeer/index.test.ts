import { BadRequest } from "../../../shared/customErrors/badRequest";
import { validateBeerInput } from "./validator";
import { BeerInput } from "./type";

describe("validateBeerInput", () => {
  it("should throw BadRequest error if no input is provided", () => {
    expect(() => validateBeerInput({} as BeerInput)).toThrow(BadRequest);
  });

  it("should throw BadRequest error if maxTemperature is less than minTemperature", () => {
    const beer: BeerInput = {
      style: "IPA",
      minTemperature: 8,
      maxTemperature: 5,
    };

    expect(() => validateBeerInput(beer)).toThrow(BadRequest);
  });

  it("should not throw any error if input is valid", () => {
    const beer: BeerInput = {
      style: "Pilsner",
      minTemperature: 4,
      maxTemperature: 6,
    };

    expect(() => validateBeerInput(beer)).not.toThrow();
  });
});
