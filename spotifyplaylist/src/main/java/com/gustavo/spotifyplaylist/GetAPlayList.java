package com.gustavo.spotifyplaylist;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAPlayList {

    //private static final String userId = "guuholi";
    //private static final String playlistId = "2mN8oGJa8prWJBqQbXcPj8";

    private static final SpotifyApi spotifyApi = ClientCredentialsExample.clientCredentials_Sync();



    public static MyPlaylist getPlaylist_Sync(String userId, String playlistId) {
        MyPlaylist myPlaylist = new MyPlaylist();
        try {

            GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(userId, playlistId)
                    .market(CountryCode.ES)
                    .build();
            final Playlist playlist = getPlaylistRequest.execute();

            myPlaylist.setName(playlist.getName());
            List<MyTrack> tracks = new ArrayList<>();
            for (PlaylistTrack item : playlist.getTracks().getItems()) {

                tracks.add(new MyTrack(item.getTrack().getName(),
                        playlist.getTracks().getItems()[0].getTrack().getArtists()[0].getName(),
                        playlist.getTracks().getItems()[0].getTrack().getArtists()[0].getHref()));
            }

            myPlaylist.setTracks(tracks);

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return  myPlaylist;
    }

}


