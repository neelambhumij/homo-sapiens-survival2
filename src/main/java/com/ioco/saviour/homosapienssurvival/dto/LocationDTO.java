package com.ioco.saviour.homosapienssurvival.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LocationDTO {

    private long survivorId;

    @NotBlank(message = "Latitude is mandatory")
    private String latitude;

    @NotBlank(message = "Longitude is mandatory")
    private String longitude;
}
