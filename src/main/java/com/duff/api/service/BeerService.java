package com.duff.api.service;

import com.duff.api.domain.Beer;
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
            if (Math.abs(b1Differnce) > Math.abs(b2Difference)) {
                return 1;
            } else if (Math.abs(b1Differnce) < Math.abs(b2Difference)) {
                return -1;
            } else {
                return 0;
            }
        };
        beerComparator.thenComparing(Beer::getStyle);

        Optional<Beer> suggestedBeer = beers.stream().min(beerComparator);

        return suggestedBeer.orElseThrow(NotFoundException::new);
    }
}
