package com.pca.pok.translation;

import com.pca.pok.entity.YugiohCard;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class YugiohCardTranslation extends CardTranslation {

    @ManyToOne
    private YugiohCard card;
}
