package com.duff.api.client.spotify.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyAccessToken {

    @JsonProperty("access_token")
    private String accessToken;
}
