package com.pca.pok.translation;

import com.pca.pok.entity.PokemonCard;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PokemonCardTranslation extends CardTranslation {

    @ManyToOne
    private PokemonCard card;
}
