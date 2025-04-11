package com.pca.pok.service;

import com.pca.pok.dto.PokemonCardDto;
import com.pca.pok.dto.PokemonTcgSetDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Exemple d’implémentation de TcgApiService<PokemonCardDto, PokemonSetDto, PokemonSerieDto>
 * pour l'API Pokemon TCG.
 *
 * Les classes PokemonSetDto, PokemonCardDto, PokemonSerieDto
 * doivent contenir au moins les champs communs (id, name, etc.).
 *
 * Tu peux ajuster selon les champs que tu veux vraiment exploiter.
 */
@Service
public class PokTcgApiService implements TcgApiService<PokemonCardDto, PokemonTcgSetDto> {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://api.pokemontcg.io/v2";

    @Override
    public List<PokemonTcgSetDto> fetchAllSets() {
        // 1) Appeler l’endpoint /sets ? pageSize=1000
        String url = BASE_URL + "/sets?pageSize=1000";
        PokemonTcgSetApiResponse response = restTemplate.getForObject(url, PokemonTcgSetApiResponse.class);

        // 2) Mapper la réponse en List<PokemonSetDto>
        if (response == null || response.getData() == null) {
            return Collections.emptyList();
        }

        // response.getData() => List<PokemonTcgSetDto> (une classe que tu définis pour la désérialisation)
        return response.getData().stream().map(tc -> {
            PokemonTcgSetDto dto = new PokemonTcgSetDto();
            dto.setId(tc.getId());              // "xy1"
            dto.setName(tc.getName());          // "XY"
            dto.setSeries(tc.getSeries());      // "XY"
            dto.setReleaseDate(tc.getReleaseDate()); // "2014/02/05"
            // ... d’autres champs si besoin
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PokemonCardDto> fetchCardsForSet(String setId) {
        // 1) Appeler l’endpoint /cards?q=set.id:"..."
        //    Ex: /cards?q=set.id:"xy1"
        String url = BASE_URL + "/cards?q=set.id:\"" + setId + "\"&pageSize=1000";
        PokemonTcgCardApiResponse response = restTemplate.getForObject(url, PokemonTcgCardApiResponse.class);

        if (response == null || response.getData() == null) {
            return Collections.emptyList();
        }

        // 2) Mapper la réponse en List<PokemonCardDto>
        return response.getData().stream().map(tc -> {
            PokemonCardDto dto = new PokemonCardDto();
            dto.setId(tc.getId());        // "xy1-1"
            dto.setName(tc.getName());    // "Venusaur-EX"
            dto.setRarity(tc.getRarity()); // "Rare Holo EX"

            // dto.setImageUrl(tc.getImages().getSmall()); // par ex., si tu veux l'image
            // etc.
            return dto;
        }).collect(Collectors.toList());
    }

    /*@Override
    public List<PokemonSerieDto> fetchAllSeries() {
        // Comme l’API ne donne pas d’endpoint direct /series,
        // on réutilise fetchAllSets() puis on extrait la liste des séries distinctes.
        List<PokemonTcgSetDto> sets = fetchAllSets();

        // Récupérer le champ "series" (ex: "XY", "Sun & Moon", etc.)
        Set<String> distinctSeriesNames = new HashSet<>();
        for (PokemonTcgSetDto s : sets) {
            if (s.getSeries() != null) {
                distinctSeriesNames.add(s.getSeries());
            }
        }

        // Construire la liste de PokemonSerieDto
        return distinctSeriesNames.stream().map(seriesName -> {
            PokemonSerieDto dto = new PokemonSerieDto();
            dto.setName(seriesName); // ex: "XY"
            return dto;
        }).collect(Collectors.toList());
    }*/
}
