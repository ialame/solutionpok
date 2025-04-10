package com.pca.pok.dto;

import lombok.Data;

@Data
public class PokemonTcgCardDto {
    private String id;       // ex: "xy1-1"
    private String name;     // ex: "Venusaur-EX"
    private String supertype;
    private String hp;
    private String rarity;   // ex: "Rare Holo EX"
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
