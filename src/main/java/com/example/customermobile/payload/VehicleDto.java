package com.example.customermobile.payload;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class VehicleDto {
    private Long id;

    @NotEmpty
    private String type;

    @NotEmpty
    private boolean isSpecial;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String price;

    @NotEmpty
    private String pviCodeLx;

    @NotEmpty
    private String nameSearch;

    @NotEmpty
    private String parentCode;
}
