package com.pca.pok.service;

import com.pca.pok.dto.PokemonApiDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {

    private final RestTemplate restTemplate;

    public PokeApiService() {
        this.restTemplate = new RestTemplate();
    }

    public PokemonApiDto fetchPokemonById(int id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        return restTemplate.getForObject(url, PokemonApiDto.class);
    }
}
