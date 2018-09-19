package com.duff.api.controller

import com.duff.api.client.spotify.domain.Playlist
import com.duff.api.client.spotify.domain.Track
import com.duff.api.client.spotify.domain.TrackWrapper
import com.duff.api.domain.Beer
import com.duff.api.domain.SuggestedBeer
import com.duff.api.exception.ConflictException
import com.duff.api.exception.NotFoundException
import com.duff.api.service.BeerService
import com.duff.api.service.PlaylistService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CONFLICT
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class BeerControllerTest extends Specification {

    BeerService beerServiceMock
    PlaylistService playlistServiceMock
    BeerController beerController

    def setup(){
        beerServiceMock = Mock(BeerService)
        playlistServiceMock = Mock(PlaylistService)
        beerController = new BeerController(beerServiceMock, playlistServiceMock)
    }

    def "should return response entity with beer when given a style"() {
        when:
            ResponseEntity<Beer> beer = beerController.getBeer("IPA")
        then:
            1 * beerServiceMock.getBeerByStyle(_ as String) >> new Beer(style: "IPA")
            beer.statusCode == OK
            beer.hasBody()
            beer.body.style == "IPA"
    }

    def "should return not found when beer is not found"() {
        when:
            ResponseEntity<Beer> beer = beerController.getBeer("IPA")
        then:
            1 * beerServiceMock.getBeerByStyle(_ as String) >>  { throw new NotFoundException() }
            beer.statusCode == NOT_FOUND
    }

    def "should return created when beer is saved"() {
        when:
            ResponseEntity<Beer> beer = beerController.saveBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer)
            beer.statusCode == CREATED
    }
    
    def "should return conflict when beer tries to save a repeated beer"() {
        when:
            ResponseEntity<Beer> beer = beerController.saveBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer) >> { throw new ConflictException() }
            beer.statusCode == CONFLICT
    }

    def "should return internal server error when beer is not saved"() {
        when:
            ResponseEntity<Beer> beer = beerController.saveBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer) >> { throw new Exception()}
            beer.statusCode == INTERNAL_SERVER_ERROR
    }

    def "should return no content when beer is updated"() {
        when:
            ResponseEntity<Beer> beer = beerController.updateBeer("IPA", new Beer(style: "IPA"))
        then:
            1 * beerServiceMock.updateBeer(_ as Beer)
            beer.statusCode == NO_CONTENT
    }
    
    def "should return bad request when beer style is different than style in path"() {
        when:
            ResponseEntity<Beer> beer = beerController.updateBeer("IPA", new Beer(style: "Kaiser"))
        then:
            0 * beerServiceMock.updateBeer(_ as Beer)
            beer.statusCode == BAD_REQUEST
    }

    def "should return internal server error when beer is not updated"() {
        when:
            ResponseEntity<Beer> beer = beerController.updateBeer("IPA", new Beer(style: "IPA"))
        then:
            1 * beerServiceMock.updateBeer(_ as Beer) >> { throw new Exception()}
            beer.statusCode == INTERNAL_SERVER_ERROR
    }

    def "should return no content when beer is deleted"() {
        when:
            ResponseEntity<Beer> beer = beerController.removeBeer("IPA")
        then:
            1 * beerServiceMock.removeBeer(_ as String)
            beer.statusCode == NO_CONTENT
    }

    def "should return internal server error when beer is not deleted"() {
        when:
            ResponseEntity<Beer> beer = beerController.removeBeer("IPA")
        then:
            1 * beerServiceMock.removeBeer(_ as String) >> { throw new Exception()}
            beer.statusCode == INTERNAL_SERVER_ERROR
    }

    def "should return a suggested beer given a temperature"() {
        when:
            ResponseEntity<SuggestedBeer> beer = beerController.suggestBeer(-7)
        then:
            1 * beerServiceMock.getSuggestedBeer(_ as Double) >> new Beer(style: "IPA")
            1 * playlistServiceMock.searchPlaylistByName(_ as String) >> new Playlist(
                    name: "IPA SONGS", tracks: [ new TrackWrapper(track: new Track(name: "ABC")) ]
            )
            beer.statusCode == OK
            beer.hasBody()
            beer.body.beerStyle == "IPA"
            beer.body.playlist
            beer.body.playlist.name == "IPA SONGS"
            beer.body.playlist.tracks
    }

    def "should return not found exception if no beer is found"() {
        when:
            ResponseEntity<SuggestedBeer> beer = beerController.suggestBeer(-7)
        then:
            1 * beerServiceMock.getSuggestedBeer(_ as Double) >> { throw new NotFoundException()}
            0 * playlistServiceMock.searchPlaylistByName(_ as String)
            beer.statusCode == NOT_FOUND
    }

    def "should return not found when no playlist is found"() {
        when:
            ResponseEntity<SuggestedBeer> beer = beerController.suggestBeer(-7)
        then:
            1 * beerServiceMock.getSuggestedBeer(_ as Double) >> new Beer(style: "IPA")
            1 * playlistServiceMock.searchPlaylistByName(_ as String) >> { throw new NotFoundException()}
    }
}
