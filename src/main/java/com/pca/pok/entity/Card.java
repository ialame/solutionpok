package com.pca.pok.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rarity;
    private int level;

    @ManyToMany(mappedBy = "cards")
    private List<CardSet> cardSets;
}
