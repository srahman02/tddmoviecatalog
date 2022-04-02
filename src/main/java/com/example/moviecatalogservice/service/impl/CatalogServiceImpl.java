package com.example.moviecatalogservice.service.impl;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.CatalogItems;
import com.example.moviecatalogservice.service.CatalogService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Override
    @SneakyThrows
    public CatalogItems getCatalogItems(String userId) {
        CatalogItems catalogItems = new CatalogItems();
        catalogItems.setCatalogItems(Collections.singletonList(new CatalogItem("Transformer", "Transformer Description", 4)));
        return catalogItems;
    }
}
