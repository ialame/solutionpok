package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgApiResponse;
import com.pca.pok.dto.PokemonTcgCardDto;
import com.pca.pok.entity.PokemonCard;
import com.pca.pok.repository.PokemonCardRepository;
import org.springframework.stereotype.Service;


@Service
public class PokemonCardImportService {

    private final PokemonTcgApiService apiService;
    private final PokemonCardRepository cardRepository;

    public PokemonCardImportService(
            PokemonTcgApiService apiService,
            PokemonCardRepository cardRepository
    ) {
        this.apiService = apiService;
        this.cardRepository = cardRepository;
    }

    public void importCards(int page, int pageSize) {
        // 1. Récupère la page demandée
        PokemonTcgApiResponse response = apiService.fetchCards(page, pageSize);
        if (response == null || response.getData() == null) {
            return;
        }

        // 2. Pour chaque carte DTO, on convertit en entité
        for (PokemonTcgCardDto dto : response.getData()) {
            // Vérifier si la carte existe déjà en base ?
            PokemonCard existing = cardRepository.findByTcgId(dto.getId());
            if (existing != null) {
                // on skip pour éviter les doublons
                continue;
            }

            PokemonCard card = new PokemonCard();
            card.setTcgId(dto.getId());
            card.setName(dto.getName());
            card.setRarity(dto.getRarity());

            if (dto.getSet() != null) {
                card.setSetName(dto.getSet().getName());
                card.setSetSeries(dto.getSet().getSeries());
                card.setSetReleaseDate(dto.getSet().getReleaseDate());
            }

            cardRepository.save(card);
        }
    }
}
