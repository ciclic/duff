import dotenv from 'dotenv';
import SpotifyWebApi from 'spotify-web-api-node';
import { getAuth } from '../spotifyAuth';
import { spotifyApi } from '../../../infra/http/integrations/spotify';
dotenv.config();

const { SPOTIFY_CLIENT_ID, SPOTIFY_CLIENT_SECRET } = process.env;


export const findSuitablePlaylist = async (beerStyle: string) => {
  try {
    const token = await getAuth();
    spotifyApi.setAccessToken(token);
    const playlists = await spotifyApi.searchPlaylists(beerStyle)
    const playlistId = playlists.body.playlists?.items[0].id;
    if(!playlistId){
      throw new Error('not found') 
    }

    const playlist = await spotifyApi.getPlaylist(playlistId);
    return playlist;
  } catch (err: any){
    throw err;
  }
}