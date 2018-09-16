package com.duff.api.controller;

import com.duff.api.domain.Beer;
import com.duff.api.exception.NotFoundException;
import com.duff.api.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController("/beer")
public class BeerController {

    private BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
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
        } catch (Exception e) {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(CREATED);
    }

    @PutMapping
    public ResponseEntity updateBeer(@RequestBody Beer beer) {
        try {
            beerService.saveBeer(beer);
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
}
