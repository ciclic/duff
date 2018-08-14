package com.gustavo.spotifyplaylist;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;

public class MyTrack {
    private final String name;
    private final String artist;
    private final String href;

    public MyTrack(String name, String artist, String href) {
        this.name = name;
        this.artist = artist;
        this.href = href;
    }

    @Override
    public String toString() {
        return "tracks{" +
                "name:'" + name + '\'' +
                ", artist:" + artist +
                ", link:'" + href + '\'' +
                '}';
    }
}
