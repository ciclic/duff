package com.ciclic.challenge.duff.service;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class SpotifyService {
    //private static final String accessToken = "BQBE5VfR_yIGUIyLEQ0nc1MsKg0DG56Tc-ZF9CPFybRoAzmeZZwMGDBe7sdpzFs7MBK0QdtrGLH20ej1KadkaIZ3bT_dYGce1tU8fhYVZq-KqyMYNIDUf2Xswk-DoTTS0xZu_VFiXH1Y98wMWc-qaXflc3OcLT-gEQ";
    private static final String accessToken = "BQAys7osbyoWGSGeZqBIyDq1bYUyLtfRaT6SBXtIecfnux1AXm_gR2kJtDWEq5xo7XC8iKYCR-bxyotEYUlEjS5W_ZMUc4TsYjcSOcyhehKRGUsgO76mUHDMZZhNGdLTtli0j2Kn3QuHvirOehaiLrBuiTx9D9wngA";
    private static final String userId = "12185143714";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();


    public Paging<PlaylistSimplified> searchPlaylists(String term) {
        try {

            final SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(term)
                    .market(CountryCode.SE)
                    .limit(10)
                    .offset(0)
                    .build();

            return searchPlaylistsRequest.execute();
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public Paging<PlaylistTrack> getPlaylistsTracks(String clientId, String playlistId) {
        try {
            GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
                    .getPlaylistsTracks(userId, playlistId)
                    .fields("description")
                    .limit(10)
                    .offset(0)
                    .build();

            return getPlaylistsTracksRequest.execute();


        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    /*public static void searchPlaylists_Async() {
        try {
            final Future<Paging<PlaylistSimplified>> pagingFuture = searchPlaylistsRequest.executeAsync();

            // ...

            final Paging<PlaylistSimplified> playlistSimplifiedPaging = pagingFuture.get();

            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
    }*/
}
