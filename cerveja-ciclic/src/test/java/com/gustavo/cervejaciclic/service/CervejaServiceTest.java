package com.gustavo.cervejaciclic.service;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.repository.CervejaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CervejaServiceTest {


    @InjectMocks
    private CervejaService service;

    @Mock
    private CervejaRepository repository;

    @Before
    public void init(){
        List<Cerveja> lista = Arrays.asList(
                new Cerveja("Weissbier", -1, 3),
                new Cerveja("Pilsens"	,-2 , 4),
                new Cerveja("Weizenbier",	-4, 6),
                new Cerveja("Red ale"	,-5, 5),
                new Cerveja("India pale ale",	-6, 7),
                new Cerveja("IPA"	,-7, 10),
                new Cerveja("Dunkel",	-8 , 2),
                new Cerveja("Imperial Stouts",	-10, 13),
                new Cerveja("Brown ale",0,14)
        );

        when(repository.findAll()).thenReturn(lista);
    }



    @Test
    public void devePegarACervejaComOValorMaisProximo(){
        assertEquals("Dunkel", service.escolheAMelhorCerveja(-2));
    }

    @Test
    public void verificaACervejaIdeal(){
        assertEquals("IPA",  service.escolheAMelhorCerveja(1));
    }

}