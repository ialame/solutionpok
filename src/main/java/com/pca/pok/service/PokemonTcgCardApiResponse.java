package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgCardDto;
import lombok.Data;

import java.util.List;

@Data
public class PokemonTcgCardApiResponse {
    private List<PokemonTcgCardDto> data;
    private int page, pageSize, count, totalCount;
    // getters/setters
}
