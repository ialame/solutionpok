package com.pca.pok.translation;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class CardSetTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lang;
    private String name;
}
