package com.pca.pok.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pca.pok.dto.PokemonTcgCardDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PokemonSet extends CardSet {

    @Column(unique = true,length = 50)
    private String tcgSetId; // ex: "xy1"

    private String releaseDate;  // "2014/02/05"
    private String updatedAt;
    private int total;
    private String legalities;
    private String ptcgoCode;
    private String symbol;           // "xy1"
    private String logo;
}
