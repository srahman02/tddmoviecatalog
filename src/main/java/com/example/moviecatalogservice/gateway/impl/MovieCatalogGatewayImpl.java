package com.example.moviecatalogservice.gateway.impl;

import com.example.moviecatalogservice.config.GatewayConfig;
import com.example.moviecatalogservice.gateway.MovieRestGateway;
import com.example.moviecatalogservice.model.CatalogItems;
import org.springframework.web.client.RestTemplate;


public class MovieCatalogGatewayImpl implements MovieRestGateway {

    private final RestTemplate restTemplate;
    private final GatewayConfig gatewayConfig;

    public MovieCatalogGatewayImpl(RestTemplate restTemplate, GatewayConfig gatewayConfig) {
        this.restTemplate = restTemplate;
        this.gatewayConfig = gatewayConfig;
    }

    @Override
    public CatalogItems getCatalogItems(String userId) {
        return restTemplate.getForObject(getUrl(userId), CatalogItems.class);
    }

    private String getUrl(String userId) {
        return gatewayConfig.getHost() + gatewayConfig.getPath() + userId;
    }

}
