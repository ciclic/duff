package com.duff.api.service

import com.duff.api.client.spotify.SpotifyClient
import com.duff.api.client.spotify.domain.Playlist
import com.duff.api.client.spotify.domain.PlaylistWrapper
import com.duff.api.client.spotify.domain.SearchResult
import com.duff.api.exception.NotFoundException
import spock.lang.Specification

class PlaylistServiceTest extends Specification {

    SpotifyClient spotifyClientMock
    PlaylistService playlistService

    void setup() {
        spotifyClientMock = Mock(SpotifyClient)
        playlistService = new PlaylistService(spotifyClientMock)
    }

    def "should return a full playlist given a name"() {
        given:
            String playlistName = "name"
            String playlistId = "playId"
        when:
            Playlist playlist = playlistService.searchPlaylistByName("name")
        then:
            1 * spotifyClientMock.search(_ as String, _ as String) >> Optional.of(
                    new SearchResult(playlistWrapper:
                            new PlaylistWrapper(playlistName: playlistName, playlists: [ new Playlist(id: playlistId) ])
                    )
            )

            1 * spotifyClientMock.getPlaylistTracks(playlistId, 2) >> Optional.of(new Playlist(id: playlistId, tracks: []))
            playlist
            playlist.id == playlistId
            playlist.name == playlistName
    }

    def "should throw not found exception when does not found a playlist given a name"() {
        when:
            playlistService.searchPlaylistByName("name")
        then:
            1 * spotifyClientMock.search(_ as String, _ as String) >> Optional.empty()
            0 * spotifyClientMock.getPlaylistTracks(_ as String, _ as Integer)
            thrown(NotFoundException)
    }

    def "should throw not found exception when does not found a playlist with tracks given an id"() {
        when:
            playlistService.searchPlaylistByName("name")
        then:
            1 * spotifyClientMock.search(_ as String, _ as String) >> Optional.of(
                    new SearchResult(playlistWrapper:
                            new PlaylistWrapper(playlistName: "name", playlists: [ new Playlist(id: "playId") ])
                    )
            )
            1 * spotifyClientMock.getPlaylistTracks(_ as String, _ as Integer) >> Optional.empty()
            thrown(NotFoundException)
    }
}
