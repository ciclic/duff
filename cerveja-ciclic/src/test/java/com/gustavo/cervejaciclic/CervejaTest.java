package com.gustavo.cervejaciclic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CervejaTest {


    @Test
    public void deveValidarQueCervejaTemTemperaturaMinima(){
        Cerveja cerveja = new Cerveja("Weissbier", -1, 3);
        assertEquals(Integer.valueOf(-1), cerveja.getMinima());
    }

    @Test
    public void deveValidarQueCervejaTemTemperaturaMaxima(){
        Cerveja cerveja = new Cerveja("Weissbier", -1, 3);
        assertEquals(Integer.valueOf(3), cerveja.getMaxima());
    }


    @Test
    public void deveRetornarUmaListaDeCervejas(){
        ListaDeCervejas lista = new ListaDeCervejas(Arrays.asList(new Cerveja("Dunkel", -8, 2), new Cerveja("Weissbier", -5, 6)));
        assertEquals(2, lista.getCervejas().size());
    }

    @Test
    public void deveDevolverAMediaDaTemeraturaDaCervejaDunkel(){
        ListaDeCervejas lista = new ListaDeCervejas(Arrays.asList( new Cerveja("Weissbier", -5, 6), new Cerveja("Dunkel", -8, 2)));

        assertEquals("Dunkel", lista.getCervejasPorMediaDeTemperatura(-2) );
    }

    @Test
    public void deveDevolverUmNuneroNegativoParaPositivo(){
        ListaDeCervejas lista = new ListaDeCervejas(Arrays.asList( new Cerveja("Weissbier", -5, 6), new Cerveja("Dunkel", -8, 2)));

        int valor = lista.inverterNumeroPositivoParaNegativoOuPositivo(-3);

        assertEquals(3, valor);
    }

    @Test
    public void deveDevolverUmNuneroPositivoParaNegativo(){
        ListaDeCervejas lista = new ListaDeCervejas(Arrays.asList( new Cerveja("Weissbier", -5, 6), new Cerveja("Dunkel", -8, 2)));

        int valor = lista.inverterNumeroPositivoParaNegativoOuPositivo(3);

        assertEquals(-3, valor);
    }

    @Test
    public void


}
