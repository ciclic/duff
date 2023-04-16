type CheckResult<ExpectedResult, Returns> = [ExpectedResult] extends [Returns]
  ? [Returns] extends [ExpectedResult]
    ? Returns
    : never
  : never;

/* strong type checking helper for high order functions */
export function strictFn<ExpectedResult>() {
  return <Params extends any[], Result extends ExpectedResult>(
    fn: (...params: Params) => CheckResult<ExpectedResult, Result>
  ): Result => {
    return fn as any
  }
}