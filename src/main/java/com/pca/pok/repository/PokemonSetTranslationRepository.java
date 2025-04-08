package com.pca.pok.repository;

import com.pca.pok.translation.PokemonSetTranslation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonSetTranslationRepository extends JpaRepository<PokemonSetTranslation, Long> {
}
