package com.pca.pok.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PokemonTcgCardDto {
    private String id;       // ex: "xy1-1"
    private String name;     // ex: "Venusaur-EX"
    private String supertype;
    private String hp;
    private String rarity;   // ex: "Rare Holo EX"
    private SetData set;     // sous-objet { id, name, series, releaseDate }

    private String[] types;
    private String  number;
    private CardImages images;  // "images": {"small": "https://images.pokemontcg.io/hgss4/1.png","large": "https://images.pokemontcg.io/hgss4/1_hires.png"},
    private String artiste;
    private String flavorText;
    private Object legalities;



    @Data
    public static class SetData {
        private String id;           // "xy1"
        private String name;         // "XY"
        private String series;       // "XY"
        private String releaseDate;  // "2014/02/05"
        private String updatedAt;
        private int total;
        private Object legalities;
        private String ptcgoCode;
        private SetImages images;
    }
    @Data
    public static class CardImages {
        private String small;           // "xy1"
        private String large;         // "XY"
    }
    @Data
    public static class SetImages {
        private String symbol;           // "xy1"
        private String logo;         // "XY"
    }

}
