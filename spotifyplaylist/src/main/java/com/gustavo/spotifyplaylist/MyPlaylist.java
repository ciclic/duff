package com.gustavo.spotifyplaylist;

import java.util.List;

public class MyPlaylist {
    private String name;
    private List<MyTrack> tracks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyTrack> getTracks() {
        return tracks;
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
