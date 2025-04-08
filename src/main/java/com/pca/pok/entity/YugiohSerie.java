package com.pca.pok.entity;

import com.pca.pok.entity.Serie;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class YugiohSerie extends Serie {
    // Attributs spécifiques à une série Yu-Gi-Oh
    private String era; // ex: Duel Monsters, GX, 5D's...
}
