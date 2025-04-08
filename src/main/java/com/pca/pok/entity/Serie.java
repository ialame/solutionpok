package com.pca.pok.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "serie")
    private List<CardSet> cardSets;
}
