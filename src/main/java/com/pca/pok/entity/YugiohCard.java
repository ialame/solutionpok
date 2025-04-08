package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class YugiohCard extends Card {

    private int attackPoints;
    private int defensePoints;
    private String attribute;
}
