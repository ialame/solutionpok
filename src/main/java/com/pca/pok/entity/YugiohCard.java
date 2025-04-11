package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class YugiohCard extends Card {

    private int attackPoints;
    private int defensePoints;
    private String attribute;
}
