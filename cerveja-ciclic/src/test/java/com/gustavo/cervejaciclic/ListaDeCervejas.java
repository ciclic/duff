package com.gustavo.cervejaciclic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaDeCervejas {

    List<Cerveja> cervejas = new ArrayList<>();

    public ListaDeCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }


    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public String getCervejasPorMediaDeTemperatura(int i) {
        //cervejas.sort(Comparator.comparing(c -> (c.getMinima()+c.getMaxima())/2) <= i );
        List<Cerveja> cervejasNaTemperatura = new ArrayList<>();

        for(int j = 0; j <= cervejas.size(); j++){

            Integer media = (cervejas.get(j).getMinima()+cervejas.get(j).getMaxima())/2;


            if(media == i){
                cervejasNaTemperatura.add(cervejas.get(0));
            }

            int diferenca = inverterNumeroPositivoParaNegativoOuPositivo(media);



        }

        return cervejas.get(0).getEstilo();
    }


    public int inverterNumeroPositivoParaNegativoOuPositivo(int valor) {
        return valor*-1;
    }
}
