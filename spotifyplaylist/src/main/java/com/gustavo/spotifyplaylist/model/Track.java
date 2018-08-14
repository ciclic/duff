package com.gustavo.spotifyplaylist.model;


import lombok.Data;

@Data
public class Track {
    private final String name;
    private final String artist;
    private final String link;

    public Track(String name, String artist, String href) {
        this.name = name;
        this.artist = artist;
        this.link = href;
    }

}
