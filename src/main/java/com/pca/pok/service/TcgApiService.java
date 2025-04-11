package com.pca.pok.service;

import com.pca.pok.dto.CardDto;
import com.pca.pok.dto.SetDto;

import java.util.List;

public interface TcgApiService<C extends CardDto, S extends SetDto> {

    List<S> fetchAllSets();
    List<C> fetchCardsForSet(String setId);
}

