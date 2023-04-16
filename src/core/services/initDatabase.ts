import { Beer } from "../domain/schemas/Beer"

export const initDatabase = async () => {
  const beers = [
    { style: "Weissbier", minTemperature: -1, maxTemperature: 3 },
    { style: "Pilsens", minTemperature: -2, maxTemperature: 4 },
    { style: "Weizenbier", minTemperature: -4, maxTemperature: 6 },
    { style: "Red ale", minTemperature: -5, maxTemperature: 5 },
    { style: "India pale ale", minTemperature: -6, maxTemperature: 7 },
    { style: "IPA", minTemperature: -7, maxTemperature: 10 },
    { style: "Dunkel", minTemperature: -8, maxTemperature: 2 },
    { style: "Imperial Stouts", minTemperature: -10, maxTemperature: 13 },
    { style: "Brown ale", minTemperature: 0, maxTemperature: 14 },
  ]
  try {
    await Beer.insertMany(beers);
  } catch (err) {
    throw err;
  }
}