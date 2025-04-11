package com.pca.pok.translation;

import com.pca.pok.entity.PokemonCard;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PokemonCardTranslation extends CardTranslation {

    @ManyToOne
    private PokemonCard card;
}
