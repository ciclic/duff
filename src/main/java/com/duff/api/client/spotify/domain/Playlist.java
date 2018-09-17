package com.duff.api.client.spotify.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Playlist {

    @JsonIgnore
    @JsonProperty("id")
    private String id;

    private String name;

    @JsonProperty(value = "items")
    private List<TrackWrapper> tracks;
}
