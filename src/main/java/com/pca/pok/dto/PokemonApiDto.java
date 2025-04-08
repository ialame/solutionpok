package com.pca.pok.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonApiDto {
    private int id;
    private String name;
    private List<TypeSlot> types;

    // Getters/setters

    public static class TypeSlot {
        private TypeData type;
        // Getter / setter
    }

    public static class TypeData {
        private String name;
        // Getter / setter
    }
}
