package com.duff.api.service;

import com.duff.api.domain.Beer;
import com.duff.api.exception.ConflictException;
import com.duff.api.exception.NotFoundException;
import com.duff.api.repository.BeerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        beerRepository
                .findById(beer.getStyle())
                .ifPresentOrElse(beer1 -> {
                    throw new ConflictException("The beer ".concat(beer1.getStyle()).concat("already exists!"));
                }, () -> beerRepository.save(beer));
    }

    public void updateBeer(Beer beer) {
        beerRepository.save(beer);
    }

    public void removeBeer(String style) {
        beerRepository.deleteById(style);
    }

    public Beer getSuggestedBeer(double temperature) {
        List<Beer> beers = beerRepository.findAllByMinTemperatureLessThanEqualAndMaxTemperatureGreaterThanEqual(temperature, temperature);

        Comparator<Beer> beerComparator = (beer1, beer2) -> {
            double b1Differnce = temperature - beer1.getAverage();
            double b2Difference = temperature - beer2.getAverage();
            return Double.compare(Math.abs(b1Differnce), Math.abs(b2Difference));
        };
        beerComparator.thenComparing(Beer::getStyle);

        Optional<Beer> suggestedBeer = beers.stream().min(beerComparator);

        return suggestedBeer.orElseThrow(NotFoundException::new);
    }
}
