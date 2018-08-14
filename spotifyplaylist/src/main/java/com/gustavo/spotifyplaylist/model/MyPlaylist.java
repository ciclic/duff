package com.gustavo.spotifyplaylist.model;

import lombok.Data;

import java.util.List;

@Data
public class MyPlaylist {
    private String name;
    private List<MyTrack> tracks;
}
