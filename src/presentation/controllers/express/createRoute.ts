import { Router, Request, Response } from 'express';

type HttpMethod = 'get' | 'post' | 'put' | 'delete';
type RouteHandler = (req: Request, res: Response) => Promise<void> | Promise<Response<any, Record<string, any>>>;

const createRoute = (
  method: HttpMethod,
  path: string,
  router: Router,
  handler: RouteHandler,
): void => {
  router[method](path, async (req: Request, res: Response) => {
    try {
      await handler(req, res);
    } catch (err: any) {
      const status = err.status || 500;
      console.error(`Error processing ${method.toUpperCase()} ${path}`, err);
      res.status(status).json({
        err,
        message: err.message
      });
      // res.status(500).send('Internal Server Error');
    }
  });
};

export default createRoute;
