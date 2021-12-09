package com.ioco.saviour.homosapienssurvival.controller;

import com.ioco.saviour.homosapienssurvival.dto.LocationDTO;
import com.ioco.saviour.homosapienssurvival.dto.RobotDTO;
import com.ioco.saviour.homosapienssurvival.dto.SurvivorDTO;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import com.ioco.saviour.homosapienssurvival.service.SurvivorService;
import com.ioco.saviour.homosapienssurvival.util.SurvivorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/survivor")
@Api(value = "HOMO SAPIENS SURVIVAL API")
@Slf4j
public class SurvivorController {

    @Autowired
    private SurvivorService survivorService;

    @ApiOperation(
            value = "Create Survivors",
            code = 200,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Survivor.class)
    @PostMapping("/create")
    ResponseEntity<Survivor> createSurvivor(@Valid @RequestBody SurvivorDTO survivorDTO) {
        Survivor survivor = SurvivorUtil.populateSurvivor(survivorDTO);
        survivorService.createSurvivor(survivor);
        log.info("Survivor Created Id:"+ survivor.getId());
        return new ResponseEntity(survivor, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update locations for Survivors",
            code = 200)
    @PostMapping("/updateLocation")
    public void updateSurvivor(@Valid @RequestBody LocationDTO locationDTO) {
        survivorService.updateSurvivorLocation(locationDTO);
        log.info("Survivor Location Updated Id:"+ locationDTO.getSurvivorId());
    }

    @ApiOperation(
            value = "Flag Infected Survivors",
            code = 200)
    @PostMapping("/flagInfected")
    public void flagInfection(@RequestBody long survivorId) {
        survivorService.flagInfection(survivorId);
        log.info("Survivor infected flagged Id:"+ survivorId);
    }

    @ApiOperation(
            value = "get list of Infected Survivors",
            code = 200,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Survivor.class)
    @GetMapping(value="/fetchInfectedSurvivors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survivor>> getInfectedSurvivors(){
        List<Survivor> infectedSurvivors = survivorService.findAllInfectedSurvivor();
        log.info("Infected Survivour List size:"+ infectedSurvivors.size());
        return new ResponseEntity<>(infectedSurvivors,HttpStatus.OK);
    }

    @ApiOperation(
            value = "get list of NonInfected Survivors",
            code = 200,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Survivor.class)
    @GetMapping(value="/fetchNonInfectedSurvivors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survivor>> getNonInfectedSurvivors(){
        List<Survivor> nonInfectedSurvivors = survivorService.findAllNonInfectedSurvivor();
        log.info("Non Infected Survivour List size:"+ nonInfectedSurvivors.size());
        return new ResponseEntity<>(nonInfectedSurvivors,HttpStatus.OK);
    }
}
