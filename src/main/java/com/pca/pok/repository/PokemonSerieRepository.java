package com.pca.pok.repository;

import com.pca.pok.entity.PokemonSerie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonSerieRepository extends JpaRepository<PokemonSerie, Long> {
}
