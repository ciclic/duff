package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.service.SearchService;
import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.service.SpotifyService;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
public class SearchController {

    private SearchService service;

    private SpotifyService spotifyService;

    @Autowired
    public SearchController(final SearchService service, final SpotifyService spotifyService) {
        this.service = service;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/{temperature}")
    public Beer getBeerWithTemperature(@PathVariable("temperature") Integer temperature) {
        return service.getBeerWithTemperature(temperature);
    }

    @GetMapping("/playlist")
    public Paging<PlaylistTrack> getPlaylist(@RequestParam("query") String term) {
        Paging<PlaylistSimplified> list =  spotifyService.searchPlaylists(term);

        if(list.getItems().length == 0) {
            return null;
        } else {
            String playlistId = list.getItems()[0].getId();
            String clientId = list.getItems()[0].getOwner().getId();

            return spotifyService.getPlaylistsTracks(clientId, playlistId);
        }

    }
}
