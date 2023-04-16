export function timeLog(req: any, res: any, next: () => void) {
  console.log('Time: ', Date.now());
  next();
}
