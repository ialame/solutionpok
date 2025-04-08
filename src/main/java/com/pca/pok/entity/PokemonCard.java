package com.pca.pok.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PokemonCard extends Card {

    private String pokemonType;
    private int hp;
    private String energyType;

    @Column(unique = true)
    private String tcgId;       // L'ID propre à la TCG API, ex: "xy1-1"
    private String name;        // Nom de la carte, ex: "Venusaur-EX"
    private String rarity;      // Rareté, ex: "Rare Holo EX"
    private String setName;     // ex: "XY"
    private String setSeries;   // ex: "XY"
    private String setReleaseDate; // ex: "2014/02/05", etc.
}
