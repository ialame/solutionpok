package com.pca.pok.dto;

import lombok.Data;

@Data
public class PokemonTcgCardDto {
    private String id;       // ex: "xy1-1"
    private String name;     // ex: "Venusaur-EX"
    private String rarity;   // ex: "Rare Holo EX"
    private SetData set;     // sous-objet { id, name, series, releaseDate }

    // Les champs supertype, subtypes, etc. si tu veux

    @Data
    public static class SetData {
        private String id;           // "xy1"
        private String name;         // "XY"
        private String series;       // "XY"
        private String releaseDate;  // "2014/02/05"
    }
}
