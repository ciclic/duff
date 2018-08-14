package com.gustavo.cervejaciclic.resource;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.service.CervejaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CervejaResource.class)
public class CervejaResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CervejaService service;

    @Test
    public void getTheBestBeer() throws Exception {

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

        given(service.escolheAMelhorCerveja(anyInt())).willReturn(lista.get(6).getEstilo());

        mvc.perform(get("/cerveja/temperature/{value}", -2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Dunkel"));

    }
}