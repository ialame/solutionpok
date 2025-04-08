package com.pca.pok.translation;

import com.pca.pok.entity.YugiohSet;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class YugiohSetTranslation extends CardSetTranslation {

    @ManyToOne
    private YugiohSet cardSet;
}
