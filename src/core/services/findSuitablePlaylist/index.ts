import dotenv from 'dotenv';
import { getAuth } from '../spotifyAuth';
import { spotifyApi } from '../../../infra/http/integrations/spotify';

dotenv.config();

export const findSuitablePlaylist = async (beerStyle: string) => {
  try {
    const token = await getAuth();
    spotifyApi.setAccessToken(token);
    const playlists = await spotifyApi.searchPlaylists(beerStyle);
    if(playlists.body.playlists?.items.length === 0) {
      throw new Error('There are no playlists matching with the beer style');
    }

    const playlistId = playlists.body.playlists?.items[0].id;
    if (!playlistId) {
      throw new Error('not found');
    }

    const playlist = await spotifyApi.getPlaylist(playlistId);
    return playlist;
  } catch (err: any) {
    throw err;
  }
};
