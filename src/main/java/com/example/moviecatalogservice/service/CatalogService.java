package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.model.CatalogItems;

public interface CatalogService {
    CatalogItems getCatalogItems(String userId);
}
