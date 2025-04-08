package com.pca.pok.repository;

import com.pca.pok.translation.PokemonSerieTranslation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonSerieTranslationRepository extends JpaRepository<PokemonSerieTranslation, Long> {
}
