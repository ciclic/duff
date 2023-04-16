import SpotifyWebApi from 'spotify-web-api-node';
import { findSuitablePlaylist } from './index';

jest.mock('spotify-web-api-node');
jest.mock('../spotifyAuth/index');

describe('findSuitablePlaylist', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should return a playlist when called with valid credentials', async () => {
    const expectedPlaylist = { name: 'Dunkel Playlist' };

    const setAccessTokenSpy = jest.fn();
    const searchPlaylistsSpy = jest
      .fn()
      .mockResolvedValue({ body: { playlists: { items: [expectedPlaylist] } } });

    // @ts-ignore
    SpotifyWebApi.mockImplementation(() => ({
      setAccessToken: setAccessTokenSpy,
      searchPlaylists: searchPlaylistsSpy,
    }));

    const actualPlaylist = await findSuitablePlaylist('Dunkel');

    expect(actualPlaylist).toEqual({ body: { playlists: { items: [expectedPlaylist] } } });
    expect(setAccessTokenSpy).toHaveBeenCalledTimes(1);
    expect(searchPlaylistsSpy).toHaveBeenCalledTimes(1);
    expect(searchPlaylistsSpy).toHaveBeenCalledWith('Dunkel');
  });

  it('should throw an error when called with invalid credentials', async () => {
    const expectedError = new Error('Unauthorized');

    const setAccessTokenSpy = jest.fn();
    const searchPlaylistsSpy = jest.fn().mockRejectedValue(expectedError);

    // @ts-ignore
    SpotifyWebApi.mockImplementation(() => ({
      setAccessToken: setAccessTokenSpy,
      searchPlaylists: searchPlaylistsSpy,
    }));

    await expect(findSuitablePlaylist()).rejects.toThrow(expectedError);
  });
});
