package com.ciclic.challenge.duff.wrapper;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist {

    private String name;
    private List<Track> tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public static Playlist builder(PlaylistSimplified PlaylistSimplified, PlaylistTrack[] playlistTrack){
        Playlist playlist = new Playlist();
        playlist.setName(PlaylistSimplified.getName());
        playlist.setTracks(Arrays.stream(playlistTrack).map(Track::build).collect(Collectors.toList()));

        return playlist;
    }
}
