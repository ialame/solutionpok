package com.pca.pok.service;

import com.pca.pok.dto.CardDto;
import com.pca.pok.dto.PokemonTcgApiResponse;
import com.pca.pok.dto.SetDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class PokemonTcgApiService implements TcgApiService {

    private final RestTemplate restTemplate;

    public PokemonTcgApiService() {
        this.restTemplate = new RestTemplate();
    }

    public PokemonTcgApiResponse fetchCards(int page, int pageSize) {
        String url = "https://api.pokemontcg.io/v2/cards?page=" + page
                + "&pageSize=" + pageSize;
        return restTemplate.getForObject(url, PokemonTcgApiResponse.class);
    }

    @Override
    public List<SetDto> fetchAllSets() {
        return List.of();
    }

    @Override
    public List<CardDto> fetchCardsForSet(String setId) {
        return List.of();
    }

}
