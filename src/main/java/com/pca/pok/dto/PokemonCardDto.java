package com.pca.pok.dto;

import lombok.Data;

public class PokemonCardDto extends CardDto {
    private int hp;
    private String supertype;
    private PokemonTcgSetDto set;     // sous-objet { id, name, series, releaseDate }
    private String[] types;
    private String  number;
    private CardImages images;  // "images": {"small": "https://images.pokemontcg.io/hgss4/1.png","large": "https://images.pokemontcg.io/hgss4/1_hires.png"},
    private String artiste;
    private String flavorText;
    private Object legalities;

    @Data
    public static class CardImages {
        private String small;           // "xy1"
        private String large;         // "XY"
    }



}