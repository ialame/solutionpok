package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PokemonSet extends CardSet {

    private String symbol;
}
