package com.pca.pok.controller;

import com.pca.pok.dto.PokemonSerieAndSetDto;
import com.pca.pok.dto.PokemonTcgSetDto;
import com.pca.pok.entity.PokemonCard;
import com.pca.pok.entity.PokemonSet;
import com.pca.pok.repository.PokemonSetRepository;
import com.pca.pok.service.PokemonCardImportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {


    private final PokemonSetRepository setRepo;
    private final PokemonCardImportService importService;

    public PokemonController(PokemonSetRepository setRepo, PokemonCardImportService importService) {
        this.setRepo = setRepo;
        this.importService = importService;
    }

    // GET /api/pokemon/series
    @GetMapping("/series")
    public PokemonSerieAndSetDto[] getAllSeries() {
        // Récupérer tous les sets
        // Extraire les .getSeries() distincts
        return importService.importAllSetsAndSeries();
    }

    // GET /api/pokemon/series
    @GetMapping("/series0")
    public List<String> getAllSeries0() {
        // Récupérer tous les sets
        List<PokemonSet> sets = setRepo.findAll();
        // Extraire les .getSeries() distincts
        return sets.stream()
                .map(PokemonSet::getSeries)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }


    // GET /api/pokemon/series/{seriesName}/sets
    @GetMapping("/series/{seriesName}/sets")
    public List<PokemonSet> getSetsForSeries(@PathVariable String seriesName) {
        return setRepo.findBySeries(seriesName);
    }

    // GET /api/pokemon/sets/{setId}/cards
    @GetMapping("/sets/{setId}/cards")
    public List<PokemonCard> getCardsForSet(@PathVariable Long setId) {
        PokemonSet setEntity = setRepo.findById(setId)
                .orElseThrow(() -> new RuntimeException("Set not found"));

        // setEntity.getCards() => List<Card>
        // On caste chaque Card en PokemonCard
        return setEntity.getCards().stream()
                .map(card -> (PokemonCard) card) // cast
                .collect(Collectors.toList());
    }


}

