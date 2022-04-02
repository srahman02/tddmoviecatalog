package com.example.moviecatalogservice.service

import com.example.moviecatalogservice.model.CatalogItem
import com.example.moviecatalogservice.model.CatalogItems
import com.example.moviecatalogservice.service.impl.CatalogServiceImpl
import spock.lang.Specification

class CatalogServiceSpec extends Specification {
    CatalogService catalogService

    def setup() {
        catalogService = new CatalogServiceImpl()
    }

    def "should return a list of catalog when called with a userId"() {
        given:
        def userId = "1"
        def catalogItem = [name: "Transformer", desc: "Transformer Description", rating: 4.0] as CatalogItem
        def catalogItems = new CatalogItems()
        catalogItems.catalogItems = [catalogItem]

        when:
        CatalogItems actualItems = catalogService.getCatalogItems(userId)
        then:
        actualItems == catalogItems
    }
}
