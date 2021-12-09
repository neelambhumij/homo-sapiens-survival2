package com.ioco.saviour.homosapienssurvival.controller;

import com.ioco.saviour.homosapienssurvival.RobotType;
import com.ioco.saviour.homosapienssurvival.dto.RobotDTO;
import com.ioco.saviour.homosapienssurvival.service.RobotService;
import com.ioco.saviour.homosapienssurvival.service.SurvivorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api(value = "HOMO SAPIENS SURVIVAL REPORT API")
public class ReportController {

    @Autowired
    private RobotService robotService;

    @Autowired
    private SurvivorService survivorService;

    @ApiOperation(
            value = "get list of Robots",
            code = 200,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = RobotDTO.class)
    @GetMapping(value="/fetchRobotList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RobotDTO>> getRobotDetails(){
        List<RobotDTO> robotDTOS = robotService.fetchRobotDetails();
        log.info("Robots List size:"+ robotDTOS.size());
        return new ResponseEntity<>(robotDTOS, HttpStatus.OK);
    }

    @ApiOperation(
            value = "get list of Robots based on RobotType",
            code = 200,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = RobotDTO.class)
    @GetMapping(value="/fetchRobotListBasedOnType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RobotDTO>> getRobotDetailsPerType(@RequestParam RobotType robotType){
        List<RobotDTO> robotDTOS = robotService.fetchRobotDetails(robotType.name());
        log.info("Robots List size: for "+ robotType + "-" +robotDTOS.size());
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
