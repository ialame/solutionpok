package com.pca.pok.repository;

import com.pca.pok.translation.PokemonCardTranslation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonCardTranslationRepository extends JpaRepository<PokemonCardTranslation, Long> {
}
