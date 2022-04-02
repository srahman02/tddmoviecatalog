package com.example.moviecatalogservice.gateway;

import com.example.moviecatalogservice.model.CatalogItems;

public interface MovieRestGateway {
    CatalogItems getCatalogItems(String userId);
}
