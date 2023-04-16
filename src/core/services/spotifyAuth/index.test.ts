import axios from 'axios';
import queryString from "node:querystring";
import { getAuth } from '.';

jest.mock('axios');

describe('getAuth', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should return an access token when called with valid credentials', async () => {
    const expectedToken = 'test_token';

    const axiosPostSpy = jest.spyOn(axios, 'post').mockResolvedValue({
      data: {
        access_token: expectedToken,
      },
    });

    const actualToken = await getAuth();

    expect(actualToken).toEqual(expectedToken);
    expect(axiosPostSpy).toHaveBeenCalledTimes(1);
    expect(axiosPostSpy).toHaveBeenCalledWith(
      `${process.env.SPOTIFY_API_URL}/token`,
      queryString.stringify({ grant_type: 'client_credentials' }),
      {
        headers: {
          Authorization:
            'Basic ' +
            new Buffer(
              `${process.env.SPOTIFY_CLIENT_ID}:${process.env.SPOTIFY_CLIENT_SECRET}`
            ).toString('base64'),
        },
      }
    );
  });

  it('should throw an error when called with invalid credentials', async () => {
    const expectedError = new Error('Unauthorized');

    jest.spyOn(axios, 'post').mockRejectedValue(expectedError);

    await expect(getAuth()).rejects.toThrow(expectedError);
  });
});
