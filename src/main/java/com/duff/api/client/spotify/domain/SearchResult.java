package com.duff.api.client.spotify.domain;

import com.duff.api.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchResult {

    @JsonProperty("playlists")
    private PlaylistWrapper playlistWrapper;

    @JsonIgnore
    public String getFirstPlaylistId() {
        Playlist playlist = playlistWrapper.getPlaylists().stream().findFirst().orElseThrow(NotFoundException::new);
        return playlist.getId();
    }
}
