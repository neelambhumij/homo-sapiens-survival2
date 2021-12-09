package com.ioco.saviour.homosapienssurvival.controller;

import com.ioco.saviour.homosapienssurvival.RobotType;
import com.ioco.saviour.homosapienssurvival.dto.RobotDTO;
import com.ioco.saviour.homosapienssurvival.service.RobotService;
import com.ioco.saviour.homosapienssurvival.service.SurvivorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/report")
@Api(value = "HOMO SAPIENS SURVIVAL REPORT API")
public class ReportController {

    @Autowired
    private RobotService robotService;

    @Autowired
    private SurvivorService survivorService;

    @GetMapping(value="/fetchRobotList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RobotDTO>> getRobotDetails(){
        List<RobotDTO> robotDTOS = robotService.fetchRobotDetails();
        return new ResponseEntity<>(robotDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/fetchRobotListBasedOnType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RobotDTO>> getRobotDetailsPerType(@RequestParam RobotType robotType){
        List<RobotDTO> robotDTOS = robotService.fetchRobotDetails(robotType.name());
        return new ResponseEntity<>(robotDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/charts")
    public String chartsHome(Model model) {
        model.addAttribute("chartDataRobot", getChartDataRobot());
        model.addAttribute("chartDataInfection", getChartDataInfection());
        return "charts";
    }

    private List<List<Object>> getChartDataRobot() {
        List<RobotDTO> flyingRobotLists = robotService.fetchRobotDetails(RobotType.Flying.name());
        List<RobotDTO> landRobotLists = robotService.fetchRobotDetails(RobotType.Land.name());
        return Arrays.asList(
                Arrays.asList("Flying Robots", flyingRobotLists.size()),
                Arrays.asList("Land Robots", landRobotLists.size())
        );
    }

    private List<List<Object>> getChartDataInfection() {
        int infectedCount = survivorService.findAllInfectedSurvivor().size();
        int nonInfectedCount = survivorService.findAllNonInfectedSurvivor().size();
        return Arrays.asList(
                Arrays.asList("Infected Survivor", infectedCount),
                Arrays.asList("Non Infected Survivor", nonInfectedCount)
        );
    }
}
