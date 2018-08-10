package com.gustavo.cervejaciclic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ListaDeCervejas {

    List<Cerveja> cervejas;

    public ListaDeCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }


    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public String getCervejasPorMediaDeTemperatura(int i) {
        //cervejas.sort(Comparator.comparing(c -> (c.getMinima()+c.getMaxima())/2) <= i );
        List<Cerveja> cervejasNaTemperatura = new ArrayList<>();

        /*for(int j = 0; j <= cervejas.size(); j++){

            Integer media = (cervejas.get(j).getMinima()+cervejas.get(j).getMaxima())/2;


            if(media == i){
                cervejasNaTemperatura.add(cervejas.get(0));
            }

            int diferenca = inverterNumeroPositivoParaNegativoOuPositivo(media);



        }*/

        return cervejas.get(0).getEstilo();
    }


    public int inverterNumeroPositivoParaNegativoOuPositivo(int valor) {
        return valor*-1;
    }

    public String verificaTemperaturaIgual(int temperaturaDesejada) {
        List<TemperaturaMediaDaCerveja> collect = cervejas.stream()
                .map(c ->
                        new TemperaturaMediaDaCerveja(c.getEstilo(), (c.getMinima() + c.getMaxima()) / 2)).collect(Collectors.toList());


        List<TemperaturaMediaDaCerveja> listaDasCervejas = new ArrayList<>();


        for(int i = 0; i < collect.size() -1; i++){

            if(collect.get(i).getTemperaturaMedia() == temperaturaDesejada){
                listaDasCervejas.add(collect.get(i));
            }


            int diferenca = pegaDiferença(temperaturaDesejada, (inverterNumeroPositivoParaNegativoOuPositivo(collect.get(i).getTemperaturaMedia()) ));

            int diferencaProxima = 0;
            if(i < collect.size() -1 ) {
                 diferencaProxima =  pegaDiferença(temperaturaDesejada, (inverterNumeroPositivoParaNegativoOuPositivo(collect.get(i+1).getTemperaturaMedia()) ));
            }else{
                diferencaProxima =  pegaDiferença(temperaturaDesejada, (inverterNumeroPositivoParaNegativoOuPositivo(collect.get(i).getTemperaturaMedia()) ));
            }

            if(diferenca < diferencaProxima){
                listaDasCervejas.add(collect.get(i));
            }

        }

        return listaDasCervejas.get(0).getEstilo();
    }

    private int pegaDiferença(int temperaturaDesejada,  int media) {
        if(media == 0)
            return inverterNumeroPositivoParaNegativoOuPositivo(temperaturaDesejada);
        return temperaturaDesejada +  media;
    }
}
