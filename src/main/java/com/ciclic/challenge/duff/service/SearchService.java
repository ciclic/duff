package com.ciclic.challenge.duff.service;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

@Service
public class SearchService {

    private Logger log = LoggerFactory.getLogger(SearchService.class);

    private BeerRepository repository;

    @Autowired
    public SearchService(BeerRepository repository) {
        this.repository = repository;
    }

    //TODO: use ehCache
    public Beer getBeerWithTemperature(int temperature){
        log.info("Getting beer with temperature {}", temperature);

        Map<Double, List<Beer>> beerMap = StreamSupport.stream(repository.findAll().spliterator(), false)
                                                 .collect(groupingBy(elem -> Math.abs(elem.getTemp() - temperature )));

        Double key = StreamSupport.stream(beerMap.keySet().spliterator(), false).min(Double::compare).get();
        beerMap.get(key).sort(Comparator.comparing(Beer::getStyle));

        return beerMap.get(key).get(0);
    }
}
