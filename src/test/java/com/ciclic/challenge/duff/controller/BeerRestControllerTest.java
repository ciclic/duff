package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import com.ciclic.challenge.duff.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;


@WebMvcTest(BeerRestController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
@WebAppConfiguration
public class BeerRestControllerTest {

    private RestDocumentationResultHandler document;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService service;

    @MockBean
    private BeerRepository repository;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Before
    public void setUp() {
        this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }

    @Test
    public void getAllTest() throws Exception {

        List<Beer> allBeers = new ArrayList(){{
            add(new Beer(1l, "Skol", -4, 2));
        }};

        Mockito.when(repository.findAll()).thenReturn(allBeers);

        this.mockMvc.perform(get("/beers/"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(repository).deleteById(any(Long.class));

        this.mockMvc.perform(delete("/beers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void insertTest() throws Exception {
        Beer beer = new Beer(1l,"Skol", -4, 2);

        Mockito.when(repository.save(beer)).thenReturn(beer);

        this.mockMvc.perform(
                put("/beers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsBytes(beer)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Beer beer = new Beer(1l, "Skol", -4, 2);

        Mockito.when(repository.save(beer)).thenReturn(beer);

        this.mockMvc.perform(
                put("/beers/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsBytes(beer)))
                .andExpect(status().isAccepted());
    }
}
