package com.pca.pok.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PokemonTcgSetDto extends SetDto {
    private String updatedAt;
    private int total;
    private Object legalities;
    private String ptcgoCode;
    private SetImages images;

    @Data
    public static class SetImages {
        private String symbol;           // "xy1"
        private String logo;         // "XY"
    }
}

