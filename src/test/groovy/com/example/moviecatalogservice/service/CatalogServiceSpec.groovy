package com.example.moviecatalogservice.service

import com.example.moviecatalogservice.exception.ItemsNotFoundException
import com.example.moviecatalogservice.gateway.MovieRestGateway
import com.example.moviecatalogservice.model.CatalogItem
import com.example.moviecatalogservice.model.CatalogItems
import com.example.moviecatalogservice.service.impl.CatalogServiceImpl
import spock.lang.Specification

class CatalogServiceSpec extends Specification {
    CatalogService catalogService
    MovieRestGateway mockGatewayService = Mock()

    def setup() {
        catalogService = new CatalogServiceImpl(mockGatewayService)
    }

    def "should return a list of catalog when called with a userId"() {
        given:
        def userId = "1"
        def catalogItem = [name: "Transformer", desc: "Transformer Description", rating: 4.0] as CatalogItem
        def expectedItems = new CatalogItems()
        expectedItems.catalogItems = [catalogItem]

        when:
        CatalogItems actualItems = catalogService.getCatalogItems(userId)
        then:
        1 * mockGatewayService.getCatalogItems(userId) >> expectedItems
        actualItems == expectedItems
    }

    def "should throw ItemNotFoundException if the gateway call fails with 404"() {
        given:
        def userId = "1"

        when:
        catalogService.getCatalogItems(userId)
        then:
        1 * mockGatewayService.getCatalogItems(userId) >> { throw new ItemsNotFoundException() }
        thrown ItemsNotFoundException
    }
}
