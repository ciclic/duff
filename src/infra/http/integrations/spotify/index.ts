import dotenv from 'dotenv';
import SpotifyWebApi from 'spotify-web-api-node';

dotenv.config();

const { SPOTIFY_CLIENT_ID, SPOTIFY_CLIENT_SECRET } = process.env;

export const spotifyApi = new SpotifyWebApi({
  clientId: SPOTIFY_CLIENT_ID,
  clientSecret: SPOTIFY_CLIENT_SECRET,
  redirectUri: 'http://www.example.com/callback',
});
