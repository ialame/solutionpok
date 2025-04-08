package com.pca.pok.translation;

import com.pca.pok.entity.SerieTranslation;
import com.pca.pok.entity.PokemonSerie;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PokemonSerieTranslation extends SerieTranslation {

    @ManyToOne
    private PokemonSerie serie;
}
