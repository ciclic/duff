package com.duff.api.client.spotify.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistWrapper {

    @JsonProperty("items")
    private List<Playlist> playlists;
}
