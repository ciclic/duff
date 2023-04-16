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
    } catch (err) {
      console.error(`Error processing ${method.toUpperCase()} ${path}`, err);
      res.status(500).send('Internal Server Error');
    }
  });
};

export default createRoute;
