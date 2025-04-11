package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgSetDto;
import lombok.Data;

import java.util.List;

@Data
public class PokemonTcgSetApiResponse {
    private List<PokemonTcgSetDto> data;
    private int page, pageSize, count, totalCount;
    // getters/setters
}
