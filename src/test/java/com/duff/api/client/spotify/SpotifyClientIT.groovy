package com.duff.api.client.spotify

import com.duff.api.ApiApplicationTests
import com.duff.api.client.spotify.domain.Playlist
import com.duff.api.client.spotify.domain.SearchResult
import org.springframework.beans.factory.annotation.Autowired

class SpotifyClientIT extends ApiApplicationTests {

    @Autowired
    SpotifyClient spotifyClient

    def "should get spotify access token"() {
        when:
            Optional<String> optAccessToken = spotifyClient.accessToken
        then:
            optAccessToken.isPresent()
    }

    def "should get a search result given a searchTerm"() {
        when:
            Optional<SearchResult> search = spotifyClient.search("rock", "playlist")
        then:
            search.isPresent()
            search.get().playlistWrapper.playlists.size() == 1
            search.get().firstPlaylistId
    }

    def "should get a playlist with tracks given an id and size limit"() {
        given:
            int trackLimit = 2
        when:
            Optional<Playlist> playlistSearch = spotifyClient.getPlaylistTracks("37i9dQZF1DXcF6B6QPhFDv", trackLimit)
        then:
            playlistSearch
            playlistSearch.get().tracks.size() == trackLimit
    }
}
