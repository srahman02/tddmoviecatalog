package com.example.moviecatalogservice.model;

import lombok.Data;

import java.util.List;

@Data
public class CatalogItems {
    private List<CatalogItem> catalogItems;
}
