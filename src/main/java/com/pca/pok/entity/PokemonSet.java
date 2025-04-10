package com.pca.pok.entity;

import com.pca.pok.dto.PokemonTcgCardDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PokemonSet extends CardSet {

    //private String symbol;
    //@Column(length = 50)
    //private String id; // "xy1", "swsh4", etc. (c'est l'id TCG du set)

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
