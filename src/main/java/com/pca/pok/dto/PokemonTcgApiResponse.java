package com.pca.pok.dto;

import lombok.Data;
import java.util.List;

@Data
public class PokemonTcgApiResponse {
    private List<PokemonTcgCardDto> data;  // le tableau de cartes
    private int page;                     // page actuelle
    private int pageSize;
    private int count;
    private int totalCount;
}
