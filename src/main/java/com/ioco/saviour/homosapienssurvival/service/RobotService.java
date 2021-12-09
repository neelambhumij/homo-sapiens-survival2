package com.ioco.saviour.homosapienssurvival.service;

import com.ioco.saviour.homosapienssurvival.dto.RobotDTO;

import java.util.List;

public interface RobotService {

    public List<RobotDTO> fetchRobotDetails();

    public List<RobotDTO> fetchRobotDetails(String robotType);
}
