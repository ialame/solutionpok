package com.pca.pok.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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
    @JsonBackReference
    private List<CardSet> sets = new ArrayList<>();


}
