package com.ciclic.challenge.duff.controller;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import com.ciclic.challenge.duff.service.SearchService;
import com.ciclic.challenge.duff.service.SpotifyService;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

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

        Beer beer = new Beer(10l, "IPA", -4, 2);
        Mockito.when(service.getBeerWithTemperature(1)).thenReturn(beer);

        PlaylistSimplified playlistSimplified = Mockito.mock(PlaylistSimplified.class);
        Mockito.when(playlistSimplified.getName()).thenReturn("list");
        Mockito.when(playlistSimplified.getId()).thenReturn("myId");

        User mockedUser = Mockito.mock(User.class);
        Mockito.when(mockedUser.getId()).thenReturn("myId");
        Mockito.when(playlistSimplified.getOwner()).thenReturn(mockedUser);

        PlaylistSimplified[] mocked = new PlaylistSimplified[]{playlistSimplified};

        Paging<PlaylistSimplified> pagingPlaylistSimplified = Mockito.mock(Paging.class);
        Mockito.when(pagingPlaylistSimplified.getItems()).thenReturn(mocked);

        Mockito.when(spotifyService.searchPlaylists(any())).thenReturn(pagingPlaylistSimplified);

        PlaylistTrack playlistTrack = Mockito.mock(PlaylistTrack.class);
        Track track = Mockito.mock(Track.class);

        ArtistSimplified artist = Mockito.mock(ArtistSimplified.class);
        Mockito.when(artist.getName()).thenReturn("artist1");
        ArtistSimplified[] mockedArtistList = new ArtistSimplified[]{artist};

        Mockito.when(track.getName()).thenReturn("track1");
        Mockito.when(track.getHref()).thenReturn("www.example.com");
        Mockito.when(track.getArtists()).thenReturn(mockedArtistList);

        Mockito.when(playlistTrack.getTrack()).thenReturn(track);

        PlaylistTrack[] mockedPlaylistTrack = new PlaylistTrack[]{playlistTrack};

        Paging<PlaylistTrack> pagingPlaylistTrack = Mockito.mock(Paging.class);
        Mockito.when(pagingPlaylistTrack.getItems()).thenReturn(mockedPlaylistTrack);

        Mockito.when(spotifyService.getPlaylistsTracks(any(), any())).thenReturn(pagingPlaylistTrack);

        this.mockMvc.perform(get("/find/1"))
                .andExpect(status().isOk());
    }
}
