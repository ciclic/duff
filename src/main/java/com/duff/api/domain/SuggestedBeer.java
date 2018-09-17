package com.duff.api.domain;

import com.duff.api.client.spotify.domain.Playlist;
import lombok.Data;

@Data
public class SuggestedBeer {

    private String beerStyle;

    private Playlist playlist;

    private SuggestedBeer(String beerStyle, Playlist playlist) {
        this.beerStyle = beerStyle;
        this.playlist = playlist;
    }

    public static SuggestedBeer of(Beer beer, Playlist playlist) {
        return new SuggestedBeer(beer.getStyle(), playlist);
    }
}
