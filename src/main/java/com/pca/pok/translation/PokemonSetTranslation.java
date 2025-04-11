package com.pca.pok.translation;

import com.pca.pok.entity.PokemonSet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PokemonSetTranslation extends CardSetTranslation {

    @ManyToOne
    private PokemonSet cardSet;
}
