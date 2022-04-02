package com.example.moviecatalogservice.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class RestExceptionHandlerSpec extends Specification {
    RestExceptionHandler restExceptionHandler

    def setup() {
        restExceptionHandler = new RestExceptionHandler()
    }

    def "should handle item not found exception"() {
        given:
        def expectedMessage = "item not found"
        def itemNotFoundException = new ItemsNotFoundException(expectedMessage)
        when:
        ResponseEntity<ApiError> responseEntity = restExceptionHandler.handleItemNotFoundException(itemNotFoundException)
        then:
        responseEntity.getStatusCode() == HttpStatus.NOT_FOUND
        responseEntity.getBody().message == expectedMessage
    }
}
