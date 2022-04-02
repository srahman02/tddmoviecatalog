package com.example.moviecatalogservice.controller;

import com.example.moviecatalogservice.model.CatalogItems;
import com.example.moviecatalogservice.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieCatalogController {

    private final CatalogService catalogService;

    public MovieCatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalog/{userId}")
    public ResponseEntity<CatalogItems>
    getMovies(@PathVariable String userId) {
        return ResponseEntity.ok().body(catalogService.getCatalogItems(userId));
    }
}
