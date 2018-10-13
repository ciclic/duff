package com.ciclic.challenge.duff.wrapper;

public class ResultWrapper {
    private String beerStyle;
    private Playlist playlist;

    public ResultWrapper(String beerStyle, Playlist playlist) {
        this.beerStyle = beerStyle;
        this.playlist = playlist;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
