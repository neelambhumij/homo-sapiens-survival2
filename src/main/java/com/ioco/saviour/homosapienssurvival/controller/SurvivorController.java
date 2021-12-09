package com.ioco.saviour.homosapienssurvival.controller;

import com.ioco.saviour.homosapienssurvival.dto.LocationDTO;
import com.ioco.saviour.homosapienssurvival.dto.SurvivorDTO;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import com.ioco.saviour.homosapienssurvival.service.SurvivorService;
import com.ioco.saviour.homosapienssurvival.util.SurvivorUtil;
import io.swagger.annotations.Api;
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
public class SurvivorController {

    @Autowired
    private SurvivorService survivorService;

    @PostMapping("/create")
    ResponseEntity<Survivor> createSurvivor(@Valid @RequestBody SurvivorDTO survivorDTO) {
        Survivor survivor = SurvivorUtil.populateSurvivor(survivorDTO);
        survivorService.createSurvivor(survivor);
        return new ResponseEntity(survivor, HttpStatus.CREATED);
    }

    @PostMapping("/updateLocation")
    public void updateSurvivor(@Valid @RequestBody LocationDTO locationDTO) {
        survivorService.updateSurvivorLocation(locationDTO);
    }

    @PostMapping("/flagInfected")
    public void flagInfection(@RequestBody long survivorId) {
        survivorService.flagInfection(survivorId);
    }

    //List of infected vs non infected
    @GetMapping(value="/fetchInfectedSurvivors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survivor>> getInfectedSurvivors(){
        List<Survivor> infectedSurvivors = survivorService.findAllInfectedSurvivor();
        return new ResponseEntity<>(infectedSurvivors,HttpStatus.OK);
    }

    @GetMapping(value="/fetchNonInfectedSurvivors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survivor>> getNonInfectedSurvivors(){
        List<Survivor> nonInfectedSurvivors = survivorService.findAllNonInfectedSurvivor();
        return new ResponseEntity<>(nonInfectedSurvivors,HttpStatus.OK);
    }

}
