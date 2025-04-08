package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PokemonTcgApiService {

    private final RestTemplate restTemplate;

    public PokemonTcgApiService() {
        this.restTemplate = new RestTemplate();
    }

    public PokemonTcgApiResponse fetchCards(int page, int pageSize) {
        String url = "https://api.pokemontcg.io/v2/cards?page=" + page
                + "&pageSize=" + pageSize;
        return restTemplate.getForObject(url, PokemonTcgApiResponse.class);
    }
}
