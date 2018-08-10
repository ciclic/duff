package com.gustavo.cervejaciclic.service;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CervejaService {

    @Autowired
    private final CervejaRepository repository;

    public CervejaService(CervejaRepository repository) {
        this.repository = repository;
    }


    public String escolheAMelhorCerveja(int temperaturaDesejada) {
        List<Cerveja> cervejas = repository.findAll();

        List<Cerveja> listaDasCervejas = new ArrayList<>();
        for(int i = 0; i < cervejas.size() -1; i++){
            int media = (cervejas.get(i).getMinima() + cervejas.get(i).getMaxima()) / 2;
            int diferenca =  pegaDiferença(temperaturaDesejada, negativoPositivo(media) );

            if(diferenca <= 1){
                listaDasCervejas.add(cervejas.get(i));
            }
        }

        listaDasCervejas.sort(Comparator.comparing(Cerveja::getEstilo));
        return listaDasCervejas.get(0).getEstilo();
    }

    private int pegaDiferença(int temperaturaDesejada,  int media) {
        if(media == 0)
            return negativoPositivo(temperaturaDesejada);
        return temperaturaDesejada +  media;
    }

    public int negativoPositivo(int valor) {
        return valor*-1;
    }
}
