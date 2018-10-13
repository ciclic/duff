package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.service.SearchService;
import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.service.SpotifyService;
import com.ciclic.challenge.duff.wrapper.Playlist;
import com.ciclic.challenge.duff.wrapper.ResultWrapper;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
public class SearchController {

    private SearchService service;
    private SpotifyService spotifyService;
    private Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchController(final SearchService service, final SpotifyService spotifyService) {
        this.service = service;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/{temperature}")
    public ResultWrapper getBeerWithTemperature(@PathVariable("temperature") Integer temperature) {
        log.info("Retrieving beer and playlist with temperature: {}", temperature);

        Beer beer =  service.getBeerWithTemperature(temperature);
        Paging<PlaylistSimplified> list =  spotifyService.searchPlaylists(beer.getStyle());

        if(list.getItems().length == 0) {
            log.warn("No beer was found");
            return null;
        } else {
            PlaylistSimplified playlistSimplified = list.getItems()[0];
            String playlistId = playlistSimplified.getId();
            String clientId = playlistSimplified.getOwner().getId();

            PlaylistTrack[] playlistTrack = spotifyService.getPlaylistsTracks(clientId, playlistId).getItems();

            ResultWrapper resultWrapper = new ResultWrapper(beer.getStyle(), Playlist.builder(playlistSimplified, playlistTrack));

            log.info("Result: {}", resultWrapper);
            return resultWrapper;
        }
    }
}
