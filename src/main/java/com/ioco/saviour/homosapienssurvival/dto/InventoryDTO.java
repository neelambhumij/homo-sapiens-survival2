package com.ioco.saviour.homosapienssurvival.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryDTO {

    private boolean water;

    private boolean food;

    private boolean medication;

    private boolean ammunition;

    @JsonIgnore
    private Survivor survivor;
}
