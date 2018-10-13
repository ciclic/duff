package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/beers")
public class BeerRestController {

    private Logger log = LoggerFactory.getLogger(BeerRestController.class);
    private BeerRepository repository;

    @Autowired
    public BeerRestController(BeerRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Beer> all() {
        log.info("Retrieving all beers");

        List<Beer> result = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());

        log.info("Beers: {}", result);
        return result;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Beer beer) {
        log.info("Updating beer {} with values {}", id, beer);

        beer.setId(id);
        repository.save(beer);

        log.info("Beer with id {} was updated", beer.getId());
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insert(@RequestBody Beer beer) {
        log.info("Inserting new beer {} ", beer);

        Beer newBeer = repository.save(beer);

        //log.info("New beer with id {} was created", newBeer.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        log.info("Deleting beer with id {} ", id);

        repository.deleteById(id);

        log.info("Beer with id {} was deleted", id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Beer> list(@PathVariable Long id) {
        return repository.findById(id);
    }
}
