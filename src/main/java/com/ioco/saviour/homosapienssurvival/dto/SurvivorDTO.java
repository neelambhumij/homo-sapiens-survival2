package com.ioco.saviour.homosapienssurvival.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioco.saviour.homosapienssurvival.Gender;
import com.ioco.saviour.homosapienssurvival.entity.Inventory;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SurvivorDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private int age;

    private Gender gender;

    @NotBlank(message = "Latitude is mandatory")
    private String latitude;

    @NotBlank(message = "Longitude is mandatory")
    private String longitude;

    private InventoryDTO inventoryDTO;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String location;

    public String getLocation() {
        return this.location = this.latitude + "," + this.longitude ;
    }


}
