package com.duff.api.client.spotify.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Playlist {

    @JsonProperty("id")
    private String id;

    @JsonProperty(value = "items")
    private List<TrackWrapper> tracks;
}
