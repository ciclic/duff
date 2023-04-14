import express from 'express';
import dotenv from 'dotenv';

dotenv.config();

const app = express();
const { PORT = 3000 } = process.env; 

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.listen(PORT, () => {
  console.log(`Servidor iniciado na porta ${PORT}`);
});