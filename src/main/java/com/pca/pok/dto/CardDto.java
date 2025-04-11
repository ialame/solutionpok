package com.pca.pok.dto;

import lombok.Data;

@Data
public class CardDto {
    private String id;       // ex: "xy1-1", "LOB-EN000"
    private String name;     // "Pikachu", "Dark Magician", ...
    private String rarity;   // "Rare", "Common", ...

}
