package com.duff.api.service

import com.duff.api.domain.Beer
import com.duff.api.exception.ConflictException
import com.duff.api.exception.NotFoundException
import com.duff.api.repository.BeerRepository
import spock.lang.Specification
import spock.lang.Unroll

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
    
    def "given a new beer should save it"() {
        when:
            beerService.saveBeer(new Beer(style: "IPA"))
        then:
            1 * beerRepositoryMock.findById(_ as String) >> Optional.empty()
            1 * beerRepositoryMock.save(_ as Beer)
    }
    
    def "given a repeated beer should not save it and throws exception"() {
        when:
            beerService.saveBeer(new Beer(style: "IPA"))
        then:
            1 * beerRepositoryMock.findById(_ as String) >> Optional.of(new Beer(style: "IPA"))
            0 * beerRepositoryMock.save(_ as Beer)
            thrown(ConflictException)
    }

    @Unroll
    def "given a temperature of #temperature should return #style beer"() {
        when:
            Beer beer = beerService.getSuggestedBeer(temperature)
        then:
            1 * beerRepositoryMock.findAllByMinTemperatureLessThanEqualAndMaxTemperatureGreaterThanEqual(temperature, temperature) >> [
                    new Beer(style:'Pilsens', minTemperature:-2, maxTemperature: 4),
                    new Beer(style:'Weissbier', minTemperature: -1, maxTemperature: 3),
                    new Beer(style:'Weizenbier', minTemperature: -4, maxTemperature: 6),
                    new Beer(style:'Red ale', minTemperature: -5, maxTemperature: 5),
                    new Beer(style:'India pale ale', minTemperature: -6, maxTemperature: 7),
                    new Beer(style:'IPA', minTemperature: -7, maxTemperature: 10),
                    new Beer(style:'Dunkel', minTemperature: -8, maxTemperature: 2),
                    new Beer(style:'Imperial Stouts', minTemperature: -10, maxTemperature: 13),
                    new Beer(style:'Brown ale', minTemperature: 0, maxTemperature: 14)
            ]
            beer.style == style
        where:
            temperature | style
            -4          | "Dunkel"
            -2          | "Dunkel"
            -1          | "Red ale"
            0           | "Red ale"
            0.5         | "India pale ale"
            1           | "Pilsens"
            1.5         | "IPA"
            3           | "IPA"
            3.70        | "IPA"
            5           | "Brown ale"
    }

    def "given a temperature that is between beers in database should throw exception"() {
        when:
            beerService.getSuggestedBeer(100)
        then:
            1 * beerRepositoryMock.findAllByMinTemperatureLessThanEqualAndMaxTemperatureGreaterThanEqual(100, 100) >> []
            thrown(NotFoundException)
    }
}
