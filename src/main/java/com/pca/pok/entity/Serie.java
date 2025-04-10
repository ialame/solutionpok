package com.pca.pok.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_type", nullable = false)
    private String gameType;

    @OneToMany(mappedBy = "serie")
    private List<CardSet> cardSets = new ArrayList<>();
}
