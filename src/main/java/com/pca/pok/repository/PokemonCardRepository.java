package com.pca.pok.repository;

import com.pca.pok.entity.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonCardRepository extends JpaRepository<PokemonCard, Long> {
    // Optionnel : on peut ajouter une méthode pour trouver par tcgId
    PokemonCard findByTcgId(String tcgId);
}
