package com.pca.pok.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PokemonCard extends Card {

    private String supertype;
    private int hp;

    @Column(unique = true)
    private String tcgId;       // L'ID propre à la TCG API, ex: "xy1-1"
    //private String name;        // Nom de la carte, ex: "Venusaur-EX"
    //private String rarity;      // Rareté, ex: "Rare Holo EX"
    private String types;
    private String  number;
    private String imageSmall;  // "images": {"small": "https://images.pokemontcg.io/hgss4/1.png","large": "https://images.pokemontcg.io/hgss4/1_hires.png"},
    private String imageLarge;
    private String artiste;
    private String flavorText;
    private String legalities;

}
