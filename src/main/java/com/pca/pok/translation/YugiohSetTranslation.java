package com.pca.pok.translation;

import com.pca.pok.entity.YugiohSet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class YugiohSetTranslation extends CardSetTranslation {

    @ManyToOne
    private YugiohSet cardSet;
}
