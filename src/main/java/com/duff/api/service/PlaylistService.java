package com.duff.api.service;

import com.duff.api.client.spotify.SpotifyClient;
import com.duff.api.client.spotify.domain.Playlist;
import com.duff.api.client.spotify.domain.SearchResult;
import com.duff.api.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private SpotifyClient spotifyClient;

    public PlaylistService(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    public Playlist searchPlaylistByName(String name) {
        SearchResult searchResult = spotifyClient.search(name, "playlist").orElseThrow(NotFoundException::new);
        String playlistName = searchResult.getPlaylistName();
        String firstPlaylistId = searchResult.getFirstPlaylistId();

        Playlist playlist = spotifyClient.getPlaylistTracks(firstPlaylistId, 2).orElseThrow(NotFoundException::new);
        playlist.setName(playlistName);

        return playlist;
    }
}
