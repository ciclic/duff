package com.duff.api.client.spotify.domain;

import lombok.Data;

@Data
public class Track {

    private Album album;

    private String name;

    private String href;
}
