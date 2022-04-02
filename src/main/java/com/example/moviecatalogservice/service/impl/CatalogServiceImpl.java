package com.example.moviecatalogservice.service.impl;

import com.example.moviecatalogservice.gateway.MovieRestGateway;
import com.example.moviecatalogservice.model.CatalogItems;
import com.example.moviecatalogservice.service.CatalogService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final MovieRestGateway movieRestGateway;

    public CatalogServiceImpl(MovieRestGateway movieRestGateway) {
        this.movieRestGateway = movieRestGateway;
    }

    @Override
    @SneakyThrows
    public CatalogItems getCatalogItems(String userId) {
        return movieRestGateway.getCatalogItems(userId);
    }
}
