package com.ciclic.challenge.duff.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SpotifyService {

    //TODO: set @Value to receive from a environment variable
    private static final String accessToken = "BQCDirHso8YZHDh2yQZiigEWdFJU8cu9kSjJLHOYY-P8SGWJ26vxLPxDg1mpiM9u7G6TvDWnUFI_gmaeBNcoVgJ451dhpDCDtvo55TZHeIL7h91iUfSYnV73dQl3rrsuYJXB4kk0-HS3MuTre-8E9EP2ZxDi0lI-rQ";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    private Logger log = LoggerFactory.getLogger(SpotifyService.class);

    public Paging<PlaylistSimplified> searchPlaylists(String term) {
        try {
            log.info("Search playlist at Spoltify with term {}", term);

            final SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(term)
                    .limit(10)
                    .offset(0)
                    .build();

            Paging<PlaylistSimplified> result = searchPlaylistsRequest.execute();

            log.info("Result: {} ", result);
            return result;
        } catch (Exception e) {
            log.error("Error: {}", e);
        }

        return null;
    }

    public Paging<PlaylistTrack> getPlaylistsTracks(String clientId, String playlistId) {
        log.info("Getting playlist track with clientId {} and playlistId ", clientId, playlistId);
        try {
            GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
                    .getPlaylistsTracks(clientId, playlistId)
                    .limit(10)
                    .offset(0)
                    .build();

            Paging<PlaylistTrack> result =  getPlaylistsTracksRequest.execute();

            log.info("Result: {} ", result);
            return result;
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
        return null;
    }
}
