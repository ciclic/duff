export const castStringToNumber = (string: string | undefined, defaultNumber: number) => 
    (isNaN(Number(string)) && Number(string)) || defaultNumber