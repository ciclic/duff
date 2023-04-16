export const castStringToNumber = (string: string | undefined, defaultNumber: number) =>
    isNaN(Number(string)) ? defaultNumber : Number(string);