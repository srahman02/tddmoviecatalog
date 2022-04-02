package com.example.moviecatalogservice.controller

import com.example.moviecatalogservice.exception.ApiError
import com.example.moviecatalogservice.exception.ItemsNotFoundException
import com.example.moviecatalogservice.exception.RestExceptionHandler
import com.example.moviecatalogservice.model.CatalogItem
import com.example.moviecatalogservice.model.CatalogItems
import com.example.moviecatalogservice.service.CatalogService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class MovieCatalogControllerSpec extends Specification {


    ObjectMapper objectMapper
    MockMvc mockMvc
    CatalogService catalogService
    def movieCatalogController

    def setup() {
        objectMapper = new ObjectMapper()
        catalogService = Mock(CatalogService)
        movieCatalogController = new MovieCatalogController(catalogService);
        mockMvc = MockMvcBuilders.standaloneSetup(movieCatalogController)
                .setControllerAdvice(new RestExceptionHandler()).build()
    }

    def "should return a list of movie catalog when /api/catalog is called for a given userId"() {
        given:
        def userId = "1"
        def catalogItem = [name: "Transformer", desc: "Transformer Description", rating: 4.0] as CatalogItem
        def expectedCatalogItems = new CatalogItems()
        expectedCatalogItems.catalogItems = [catalogItem]
        when:
        def mvcResult = mockMvc.perform(get("/api/catalog/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        def content = mvcResult.getResponse().getContentAsString();
        CatalogItems actualCatalogItems = objectMapper.readValue(content, CatalogItems.class)
        then:
        1 * catalogService.getCatalogItems(userId) >> expectedCatalogItems
        actualCatalogItems == expectedCatalogItems
    }

    def "should return 404 not found if the service call throws exception"() {
        given:
        def userId = "1"
        def errorMessage = "item not found"
        when:
        def mvcResult = mockMvc.perform(get("/api/catalog/" + userId))
                .andExpect(status().isNotFound())
                .andReturn()
        def content = mvcResult.getResponse().getContentAsString();
        ApiError error = objectMapper.readValue(content, ApiError.class)
        then:
        1 * catalogService.getCatalogItems(userId) >> { throw new ItemsNotFoundException(errorMessage) }
        error.message == errorMessage
        error.status == HttpStatus.NOT_FOUND
    }
}
