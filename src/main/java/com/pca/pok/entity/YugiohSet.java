package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class YugiohSet extends CardSet {

    private String setCode;
}
