package com.example.moviecatalogservice.gateway

import com.example.moviecatalogservice.config.GatewayConfig
import com.example.moviecatalogservice.exception.ItemsNotFoundException
import com.example.moviecatalogservice.gateway.impl.MovieCatalogGatewayImpl
import com.example.moviecatalogservice.model.CatalogItem
import com.example.moviecatalogservice.model.CatalogItems
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class MovieCatalogGatewaySpec extends Specification {
    def mockMovieCatalogGateway
    RestTemplate mockRestTemplate = Mock()
    GatewayConfig mockGateWayConfig = Mock()

    def setup() {
        mockMovieCatalogGateway = new MovieCatalogGatewayImpl(mockRestTemplate, mockGateWayConfig)

    }

    def "should return a list of movie catalog when the inner call is successful"() {
        given:
        def host = "http://localhost:8081"
        def path = "/api/catalog/"
        def userId = "1"
        def catalogItem = [name: "Transformer", desc: "Transformer Description", rating: 4.0] as CatalogItem
        def expectedCatalogItems = new CatalogItems()
        expectedCatalogItems.catalogItems = [catalogItem]
        when:
        CatalogItems catalogItems = mockMovieCatalogGateway.getCatalogItems(userId)
        then:
        1 * mockGateWayConfig.getHost() >> host
        1 * mockGateWayConfig.getPath() >> path
        1 * mockRestTemplate.getForObject(host + path + userId, CatalogItems.class) >> expectedCatalogItems
        catalogItems == expectedCatalogItems
    }

    def "should throw ItemsNotFoundException if the rest call fails with 404 status code"() {
        given:
        def host = "http://localhost:8081"
        def path = "/api/catalog/"
        def userId = "1"
        when:
        mockMovieCatalogGateway.getCatalogItems(userId)
        then:
        1 * mockGateWayConfig.getHost() >> host
        1 * mockGateWayConfig.getPath() >> path
        1 * mockRestTemplate.getForObject(host + path + userId, CatalogItems.class) >> { throw new ItemsNotFoundException("item not found") }
        thrown ItemsNotFoundException
    }

    def "should return url from properties file"() {
        given:
        def expectedUrl = "http://localhost:8081/api/catalog/1"
        def userId = "1"
        def host = "http://localhost:8081"
        def path = "/api/catalog/"
        when:
        def url = mockMovieCatalogGateway.getUrl(userId)
        then:
        1 * mockGateWayConfig.getHost() >> host
        1 * mockGateWayConfig.getPath() >> path
        url == expectedUrl
    }
}
