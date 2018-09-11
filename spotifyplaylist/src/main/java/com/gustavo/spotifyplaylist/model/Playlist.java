package com.gustavo.spotifyplaylist.model;

import lombok.Data;

import java.util.List;

@Data
public class Playlist {
    private String name;
    private List<Track> tracks;
}
