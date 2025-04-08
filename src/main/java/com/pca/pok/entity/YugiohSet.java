package com.pca.pok.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class YugiohSet extends CardSet {

    private String setCode;
}
