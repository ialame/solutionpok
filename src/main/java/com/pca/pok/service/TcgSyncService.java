package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgApiResponse;
import com.pca.pok.dto.PokemonTcgCardDto;
import com.pca.pok.entity.PokemonSet;
import com.pca.pok.repository.PokemonSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TcgSyncService {

    private final RestTemplate restTemplate;
    private final PokemonSetRepository setRepository;
    private final PokemonSerieRepository serieRepository;
    // … autres repos selon ce que tu veux stocker

    public TcgSyncService(
            PokemonSetRepository setRepository,
            PokemonSerieRepository serieRepository
            // … autres
    ) {
        this.restTemplate = new RestTemplate();
        this.setRepository = setRepository;
        this.serieRepository = serieRepository;
        // …
    }

    // Méthode qui télécharge et enregistre tout
    public void fullSync() {
        // 1) Récupérer la liste de sets (et séries) depuis l’API TCG
        // Example : GET "https://api.pokemontcg.io/v2/sets"
        String url = "https://api.pokemontcg.io/v2/sets";
        PokemonTcgApiResponse response = restTemplate.getForObject(url, PokemonTcgApiResponse.class);
        if (response == null || response.getData() == null) {
            return;
        }

        // 2) Pour chaque set, stocker la série, puis le set
        for (PokemonTcgCardDto dto : response.getData()) {
            // ex: dto.getId() => "xy1", dto.getName() => "XY", dto.getSeries() => "XY"
            // Vérifier si la série existe déjà
            Serie serie = findOrCreateSerie(dto.getSet().getSeries());

            // Vérifier si le set existe déjà
            PokemonSet setEntity = setRepository.findByTcgSetId(dto.getId()).orElse(null);
            if (setEntity == null) {
                setEntity = new PokemonSet();
                setEntity.setTcgSetId(dto.getId());
            }
            setEntity.setName(dto.getName());
            setEntity.setReleaseDate(dto.getSet().getReleaseDate());
            setEntity.setSerie(serie);

            setRepository.save(setEntity);
        }
    }

    private PokemonSerie findOrCreateSerie(String seriesName) {
        if (seriesName == null) {
            seriesName = "Unknown";
        }
        // Chercher la serie par nom
        PokemonSerie serie = serieRepository.findByName(seriesName).orElseThrow();
        if (serie == null) {
            serie = new PokemonSerie();
            serie.setName(seriesName);
            serieRepository.save(serie);
        }
        return serie;
    }
}
