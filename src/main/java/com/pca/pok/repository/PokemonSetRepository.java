package com.pca.pok.repository;

import com.pca.pok.entity.PokemonSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PokemonSetRepository extends JpaRepository<PokemonSet, Long> {
    Optional<PokemonSet> findByTcgSetId(String tcgSetId);
}
