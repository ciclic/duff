package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.DuffApplication;
import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import com.ciclic.challenge.duff.service.SearchService;
import com.ciclic.challenge.duff.service.SpotifyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

//@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
@WebAppConfiguration
public class SearchControllerTest {

    private RestDocumentationResultHandler document;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService service;

    @MockBean
    private BeerRepository repository;

    @MockBean
    private SpotifyService spotifyService;

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
    public void findTemperature() throws Exception {

        Beer beer = new Beer("Skol", -4, 2);
        Mockito.when(service.getBeerWithTemperature(1)).thenReturn(beer);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(beer);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        this.mockMvc.perform(get("/find/1"))
                //.andExpect(content().string(containsString(jsonInString)))
                .andExpect(status().isOk());
    }
}
