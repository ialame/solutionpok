package com.pca.pok.config;

import com.pca.pok.service.PokemonCardImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final PokemonCardImportService importService;

    public DatabaseSeeder(PokemonCardImportService importService) {
        this.importService = importService;
    }

    @Override
    public void run(String... args) throws Exception {
        // ex: on importe la page 1, 10 cartes
        importService.importCards(1, 10);

        System.out.println("Import Pokémon TCG page=1, pageSize=10 terminé !");
    }
}
