export const castStringToNumber = (string: string | undefined, defaultNumber: number) => (Number.isNaN(Number(string)) ? defaultNumber : Number(string));
