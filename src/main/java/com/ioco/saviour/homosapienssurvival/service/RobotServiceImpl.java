package com.ioco.saviour.homosapienssurvival.service;

import com.ioco.saviour.homosapienssurvival.dto.RobotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RobotServiceImpl implements RobotService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${robot.cpu.system}")
    private String robotCpuUrl;

    @Override
    public List<RobotDTO> fetchRobotDetails() {
        ResponseEntity<List<RobotDTO>> roboLists = restTemplate.exchange(robotCpuUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RobotDTO>>() {
                });
        return roboLists.getBody();
    }

    @Override
    public List<RobotDTO> fetchRobotDetails(String robotType) {
        List<RobotDTO> robotDTOS = fetchRobotDetails();
        List<RobotDTO> filteredList = robotDTOS.stream().filter(robot -> robot.getCategory().equals(robotType)).collect(Collectors.toList());
        return filteredList;

    }
}
