package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PokemonSerie extends Serie {

    private String region;
    private String name; // "Sword & Shield", "XY", etc.
}
