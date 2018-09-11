package com.gustavo.cervejaciclic.resource.dto;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.model.PlaylistConversion;
import lombok.Data;

@Data
public class CervejaResult {
    private final String beerStyle;
    private final PlaylistConversion playlist;

    public CervejaResult(String beerStyle, PlaylistConversion playlist) {
        super();
        this.beerStyle = beerStyle;
        this.playlist = playlist;
    }
}
