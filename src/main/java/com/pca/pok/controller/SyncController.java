package com.pca.pok.controller;

import com.pca.pok.service.PokemonCardImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

    private final PokemonCardImportService importService;

    public SyncController(PokemonCardImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/pokemon")
    public String syncPokemon() {
        importService.importAllSetsAndCards();
        return "ok";
    }
}

