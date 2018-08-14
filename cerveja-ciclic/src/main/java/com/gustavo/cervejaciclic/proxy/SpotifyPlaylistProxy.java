package com.gustavo.cervejaciclic.proxy;

import com.gustavo.cervejaciclic.model.PlaylistConversion;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul")
public interface SpotifyPlaylistProxy {

    @GetMapping("/playlist/playlist/userid/{userId}/playlistid/{playlistId}")
    PlaylistConversion getPlayListValue(@PathVariable("userId") String userId, @PathVariable("playlistId") String playlistId);
}