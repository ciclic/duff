package com.duff.api.controller;

import com.duff.api.client.spotify.domain.Playlist;
import com.duff.api.domain.Beer;
import com.duff.api.domain.SuggestedBeer;
import com.duff.api.exception.ConflictException;
import com.duff.api.exception.NotFoundException;
import com.duff.api.service.BeerService;
import com.duff.api.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(value = "/beers")
public class BeerController {

    private BeerService beerService;
    private PlaylistService playlistService;

    public BeerController(BeerService beerService, PlaylistService playlistService) {
        this.beerService = beerService;
        this.playlistService = playlistService;
    }

    @GetMapping(value = "/{style}")
    public ResponseEntity<Beer> getBeer(@PathVariable String style) {
        ResponseEntity responseEntity;
        try {
            responseEntity = ResponseEntity.ok(beerService.getBeerByStyle(style));
        } catch (NotFoundException nfe) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody Beer beer) {
        try {
            beerService.saveBeer(beer);
        } catch (ConflictException e) {
            return new ResponseEntity(CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{style}")
    public ResponseEntity updateBeer(@RequestParam String style, @RequestBody Beer beer) {
        try {
            if (!style.equals(beer.getStyle())) {
                return ResponseEntity.badRequest().build();
            }

            beerService.updateBeer(beer);
        } catch (Exception e) {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{style}")
    public ResponseEntity removeBeer(@PathVariable String style) {
        try {
            beerService.removeBeer(style);
        } catch (Exception e) {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/suggested")
    public ResponseEntity<SuggestedBeer> suggestBeer(@RequestParam("temperature") double temperature) {
        try {
            Beer suggestedBeer = beerService.getSuggestedBeer(temperature);
            Playlist playlist = playlistService.searchPlaylistByName(suggestedBeer.getStyle());

            return ResponseEntity.ok(SuggestedBeer.of(suggestedBeer, playlist));
        } catch (NotFoundException nfe) {
            return ResponseEntity.notFound().build();
        }
    }
 }
