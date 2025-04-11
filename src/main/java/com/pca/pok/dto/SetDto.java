package com.pca.pok.dto;

import lombok.Data;

@Data
public class SetDto {
    private String id;        // ex: "xy1", "LOB-EN", etc.
    private String name;      // "XY Base Set", "Legend of Blue Eyes", ...
    private String series;    // "XY", "DM", "Core Set 2021"...
    private String releaseDate; // ex: "2014-02-05"

}
