package com.gustavo.spotifyplaylist;

import java.util.List;

public class MyPlaylist {
    private String name;
    private List<MyTrack> tracks;

    public MyPlaylist(String name) {
        this.name = name;
    }

    public void setTracks(List<MyTrack> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "playlist{" +
                "name:'" + name + '\'' +
                ", tracks:" + tracks +
                '}';
    }
}
