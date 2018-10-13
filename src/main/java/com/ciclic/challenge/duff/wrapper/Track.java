package com.ciclic.challenge.duff.wrapper;

import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Track {
    private String name;
    private List<Artist> artists;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static Track build(PlaylistTrack playlistTrack){
        Track track = new Track();
        track.setArtists(Arrays.stream(playlistTrack.getTrack().getArtists()).map(elem -> new Artist(elem.getName())).collect(Collectors.toList()));
        track.setName(playlistTrack.getTrack().getName());
        track.setLink(playlistTrack.getTrack().getHref());
        return track;
    }
}
