package com.pca.pok.repository;

import com.pca.pok.entity.PokemonSerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PokemonSerieRepository extends JpaRepository<PokemonSerie, Long> {
    Optional<PokemonSerie> findByName(String name);
}
