package com.duff.api.service;

import com.duff.api.domain.Beer;
import com.duff.api.exception.NotFoundException;
import com.duff.api.repository.BeerRepository;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    private BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer getBeerByStyle(String style) {
        return beerRepository
                .findById(style)
                .orElseThrow(new NotFoundException("beer not found"));
    }

    public void saveBeer(Beer beer) {
        beerRepository.save(beer);
    }

    public void removeBeer(String style) {
        beerRepository.deleteById(style);
    }
}
