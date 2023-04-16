import dotenv from 'dotenv';
import axios from 'axios';
import queryString from 'node:querystring';

dotenv.config();

const { SPOTIFY_API_URL, SPOTIFY_CLIENT_ID, SPOTIFY_CLIENT_SECRET } = process.env;

export const getAuth = async () => {
  try {
    console.log(SPOTIFY_CLIENT_SECRET);

    const data = queryString.stringify({ grant_type: 'client_credentials' });

    const auth = await axios.post(`${SPOTIFY_API_URL}/token`, data, {
      headers: {
        Authorization: `Basic ${Buffer.from(`${SPOTIFY_CLIENT_ID}:${SPOTIFY_CLIENT_SECRET}`).toString('base64')}`,
      },
    });
    console.log(auth.data.access_token);
    return auth.data.access_token;
  } catch (e) {
    throw e;
  }
};
