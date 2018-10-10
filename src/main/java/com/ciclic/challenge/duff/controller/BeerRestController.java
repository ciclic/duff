package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
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

    @Autowired
    private BeerRepository repository;

    @GetMapping("/all")
    @ResponseBody
    public List<Beer> all() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @PostMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Beer update(@PathVariable Long id, @RequestBody Beer beer) {
        beer.setId(id);
        return repository.save(beer);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Beer insert(@RequestBody Beer beer) {
        return repository.save(beer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Beer> list(@PathVariable Long id) {
        return repository.findById(id);
    }
}
