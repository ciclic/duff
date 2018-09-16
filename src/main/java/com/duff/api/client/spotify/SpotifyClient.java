package com.duff.api.client.spotify;

import com.duff.api.client.spotify.domain.Playlist;
import com.duff.api.client.spotify.domain.SearchResult;
import com.duff.api.client.spotify.domain.SpotifyAccessToken;
import com.duff.api.configuration.SpotifyConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Component
public class SpotifyClient {

    private SpotifyConfig spotifyConfig;

    public SpotifyClient(SpotifyConfig spotifyConfig) {
        this.spotifyConfig = spotifyConfig;
    }

    public Optional<String> getAccessToken() {
        return WebClient
                .create(spotifyConfig.getAuthUri())
                .post()
                .contentType(APPLICATION_FORM_URLENCODED)
                .header(AUTHORIZATION, spotifyConfig.getAuthorizationHeader())
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(SpotifyAccessToken.class))
                .map(SpotifyAccessToken::getAccessToken)
                .blockOptional();
    }

    public Optional<SearchResult> search(String searchTerm, String type) {
        return WebClient
                .create(spotifyConfig.getUri())
                .get()
                .uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("q", searchTerm)
                        .queryParam("type", type)
                        .queryParam("limit", spotifyConfig.getSearchLimit())
                        .build())
                .header("Authorization", "Bearer " + getAccessToken().orElse(""))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(SearchResult.class))
                .blockOptional();
    }

    public Optional<Playlist> getPlaylistTracks(String playlistId, int tracksLimit) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", playlistId);

        return WebClient
                .create(spotifyConfig.getUri())
                .get()
                .uri(uriBuilder -> uriBuilder.path("/playlists/{id}/tracks")
                        .queryParam("fields", spotifyConfig.getFieldsPlaylistsTracks())
                        .queryParam("limit", tracksLimit)
                        .build(pathParams))
                .header("Authorization", "Bearer " + getAccessToken().orElse(""))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Playlist.class))
                .blockOptional();
    }
}
