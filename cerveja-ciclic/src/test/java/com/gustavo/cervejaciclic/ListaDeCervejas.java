package com.gustavo.cervejaciclic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDeCervejas {

    List<Cerveja> cervejas;

    public ListaDeCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }


    public List<Cerveja> getCervejas() {
        return cervejas;
    }


    public String escolheAMelhorCerveja(int temperaturaDesejada) {
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
