package com.duff.api.service

import com.duff.api.domain.Beer
import com.duff.api.exception.NotFoundException
import com.duff.api.repository.BeerRepository
import spock.lang.Specification

class BeerServiceTest extends Specification {

    BeerRepository beerRepositoryMock
    BeerService beerService;

    void setup() {
        beerRepositoryMock = Mock(BeerRepository)
        beerService = new BeerService(beerRepositoryMock)
    }

    def "should return a beer given a style"() {
        when:
            Beer beer = beerService.getBeerByStyle("IPA")
        then:
            1 * beerRepositoryMock.findById(_ as String) >> Optional.of(new Beer(style: "IPA", minTemperature: -2, maxTemperature: 10))
            beer.style == "IPA"
            beer.minTemperature == -2
            beer.maxTemperature == 10
            beer.average == 4.0
    }

    def "should throw exception when does not found a beer given a style"() {
        when:
            Beer beer = beerService.getBeerByStyle("IPA")
        then:
            1 * beerRepositoryMock.findById(_ as String) >> Optional.empty()
            thrown(NotFoundException)
    }
}
