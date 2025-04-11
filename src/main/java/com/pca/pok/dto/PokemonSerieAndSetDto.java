package com.pca.pok.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PokemonSerieAndSetDto {
    String name;
    List<PokemonTcgSetDto> sets =new ArrayList<>();

    public PokemonSerieAndSetDto(String name) {
        this.name = name;
    }
}
