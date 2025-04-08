package com.pca.pok.translation;

import com.pca.pok.entity.SerieTranslation;
import com.pca.pok.entity.YugiohSerie;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class YugiohSerieTranslation extends SerieTranslation {

    @ManyToOne
    private YugiohSerie serie;
}
