import dotenv from 'dotenv';
import SpotifyWebApi from 'spotify-web-api-node';
import { getAuth } from './spotifyAuth';
dotenv.config();

const { SPOTIFY_CLIENT_ID, SPOTIFY_CLIENT_SECRET } = process.env;


export const findSuitablePlaylist = async () => {
  const spotifyApi = new SpotifyWebApi({
      clientId: SPOTIFY_CLIENT_ID,
      clientSecret: SPOTIFY_CLIENT_SECRET,
      redirectUri: 'http://www.example.com/callback'
    });

  try {
    console.log("before auth")
    const token = await getAuth();
    spotifyApi.setAccessToken(token);
    console.log(token);
    const playlist = await spotifyApi.searchPlaylists('Dunkel')
    return playlist;
  } catch (err: any){
    throw err;
  }
}