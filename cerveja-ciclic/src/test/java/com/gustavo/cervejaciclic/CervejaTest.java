package com.gustavo.cervejaciclic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CervejaTest {

    private ListaDeCervejas lista;

    @Before
    public void init(){
        lista = new ListaDeCervejas(Arrays.asList(
                new Cerveja("Weissbier", -1, 3),
                new Cerveja("Pilsens"	,-2 , 4),
                new Cerveja("Weizenbier",	-4, 6),
                new Cerveja("Red ale"	,-5, 5),
                new Cerveja("India pale ale",	-6, 7),
                new Cerveja("IPA"	,-7, 10),
                new Cerveja("Dunkel",	-8 , 2),
                new Cerveja("Imperial Stouts",	-10, 13),
                new Cerveja("Brown ale",0,14)
                ));
    }



    @Test
    public void deveDevolverUmNuneroNegativoParaPositivo(){
        int valor = lista.negativoPositivo(-3);
        assertEquals(3, valor);
    }

    @Test
    public void deveDevolverUmNuneroPositivoParaNegativo(){
        int valor = lista.negativoPositivo(3);

        assertEquals(-3, valor);
    }


    @Test
    public void devePegarACervejaComOValorMaisProximo(){
         assertEquals("Dunkel", lista.escolheAMelhorCerveja(-2));
    }

    @Test
    public void verificaACervejaIdeal(){
        assertEquals("IPA",  lista.escolheAMelhorCerveja(1));
    }


}
