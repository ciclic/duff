package com.duff.api.client.spotify.domain;

import lombok.Data;

import java.util.List;

@Data
public class Album {

    private List<Artist> artists;
}
