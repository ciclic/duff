package com.gustavo.cervejaciclic.proxy;

import com.gustavo.cervejaciclic.model.PlaylistConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul")
public interface SpotifyPlaylistProxy {

    @GetMapping("/userId/{userId}/playlistId/{playlistId}")
    PlaylistConversion getPlayListValue(@PathVariable String userId, @PathVariable String playlistId);
}