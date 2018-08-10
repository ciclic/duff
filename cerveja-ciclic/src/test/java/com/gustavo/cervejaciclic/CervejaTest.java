package com.gustavo.cervejaciclic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CervejaTest {

    private ListaDeCervejas lista;

    @Before
    public void init(){
        lista = new ListaDeCervejas(Arrays.asList(new Cerveja("Dunkel", -8, 2),
                new Cerveja("Weissbier", -5, 6),
                new Cerveja("A boa", -7, 2)
                ));
    }


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
        assertEquals(3, lista.getCervejas().size());
    }

    @Test
    public void deveDevolverAMediaDaTemeraturaDaCervejaDunkel(){
        assertEquals("Dunkel", lista.getCervejasPorMediaDeTemperatura(-2) );
    }

    @Test
    public void deveDevolverUmNuneroNegativoParaPositivo(){
        int valor = lista.inverterNumeroPositivoParaNegativoOuPositivo(-3);
        assertEquals(3, valor);
    }

    @Test
    public void deveDevolverUmNuneroPositivoParaNegativo(){
        int valor = lista.inverterNumeroPositivoParaNegativoOuPositivo(3);

        assertEquals(-3, valor);
    }

    @Test
    public void verificarSeATemperaturaEIgualAUmValor(){
        assertEquals("A boa", lista.verificaTemperaturaIgual(-2));
    }

    @Test
    public void devePegarACervejaComOValorMaisProximo(){
        ListaDeCervejas lista = new ListaDeCervejas(Arrays.asList(new Cerveja("Dunkel", -8, 2),
                new Cerveja("Weissbier", -5, 6)));

        assertEquals("Dunkel", lista.verificaTemperaturaIgual(-2));


    }


}
