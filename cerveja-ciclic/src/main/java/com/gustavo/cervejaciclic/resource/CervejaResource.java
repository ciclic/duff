package com.gustavo.cervejaciclic.resource;


import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.model.PlaylistConversion;
import com.gustavo.cervejaciclic.proxy.SpotifyPlaylistProxy;
import com.gustavo.cervejaciclic.repository.CervejaRepository;
import com.gustavo.cervejaciclic.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cerveja")
public class CervejaResource {

    @Autowired
    private final CervejaService service;

    @Autowired
    private CervejaRepository repository;

    @Autowired
    private SpotifyPlaylistProxy proxy;

    public CervejaResource(CervejaService service) {
        this.service = service;
    }

    @GetMapping("/temperature/{value}")
    public Cerveja getTheBestBeer(@PathVariable Integer value){
        Cerveja cerveja = service.escolheAMelhorCerveja(value);
        PlaylistConversion playlist = proxy.getPlayListValue(cerveja.getUserId(), cerveja.getPlaylistId());
        return null ;
    }

    @GetMapping("/all")
    public Page<Cerveja> all(Pageable pageable){
        return repository.findAll(pageable);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cerveja> update(@PathVariable Long id, @Valid @RequestBody Cerveja cerveja){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(cerveja));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Cerveja> creating(@Valid @RequestBody Cerveja entity, HttpServletResponse response){
        final Cerveja save = repository.save(entity);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(save.getId()).toUri();
        response.setHeader("location", uri.toASCIIString());
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }



}
