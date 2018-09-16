package com.duff.api.controller

import com.duff.api.domain.Beer
import com.duff.api.exception.NotFoundException
import com.duff.api.service.BeerService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class BeerControllerTest extends Specification {

    BeerService beerServiceMock
    BeerController beerController

    def setup(){
        beerServiceMock = Mock(BeerService)
        beerController = new BeerController(beerServiceMock)
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

    def "should return internal server error when beer is not saved"() {
        when:
            ResponseEntity<Beer> beer = beerController.saveBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer) >> { throw new Exception()}
            beer.statusCode == INTERNAL_SERVER_ERROR
    }

    def "should return created when beer is updated"() {
        when:
            ResponseEntity<Beer> beer = beerController.updateBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer)
            beer.statusCode == NO_CONTENT
    }

    def "should return internal server error when beer is not updated"() {
        when:
            ResponseEntity<Beer> beer = beerController.updateBeer(new Beer())
        then:
            1 * beerServiceMock.saveBeer(_ as Beer) >> { throw new Exception()}
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
}
