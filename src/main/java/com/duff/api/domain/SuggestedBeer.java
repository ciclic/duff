package com.duff.api.domain;

import com.duff.api.client.spotify.domain.Playlist;
import lombok.Data;

import java.util.List;

@Data
public class SuggestedBeer {

    private String beerStyle;

    private Playlist playlist;

    public static SuggestedBeer of(Beer beer, Playlist playlist) {
        return null;
    }
}
