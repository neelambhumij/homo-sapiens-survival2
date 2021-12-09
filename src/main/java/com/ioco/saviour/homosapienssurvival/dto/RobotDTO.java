package com.ioco.saviour.homosapienssurvival.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RobotDTO  {

    private String model;

    private String serialNumber;

    private String manufacturedDate;

    private String category;
}
