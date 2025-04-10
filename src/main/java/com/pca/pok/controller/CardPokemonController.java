package com.pca.pok.controller;

import com.pca.pok.entity.PokemonCard;
import com.pca.pok.repository.PokemonCardRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:5173")
public class CardPokemonController {

    private final PokemonCardRepository pokemonCardRepository;

    public CardPokemonController(PokemonCardRepository pokemonCardRepository) {
        this.pokemonCardRepository = pokemonCardRepository;
    }

    // GET /api/cards
    @GetMapping
    public List<PokemonCard> getAllCards() {
        return pokemonCardRepository.findAll();
    }

    // GET /api/cards/{id}
    @GetMapping("/{id}")
    public PokemonCard getCardById(@PathVariable Long id) {
        return pokemonCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + id));
    }

    // Tu peux rajouter POST, PUT, DELETE si besoin
}
