package com.gustavo.cervejaciclic.model;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistConversion {

    private String name;
    private List<Track> tracks;
}
