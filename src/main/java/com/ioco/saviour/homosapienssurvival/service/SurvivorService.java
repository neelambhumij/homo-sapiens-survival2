package com.ioco.saviour.homosapienssurvival.service;

import com.ioco.saviour.homosapienssurvival.dto.LocationDTO;
import com.ioco.saviour.homosapienssurvival.entity.InfectionRecords;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;

import java.util.List;

public interface SurvivorService {

    public void createSurvivor(Survivor survivor);

    public void updateSurvivorLocation(LocationDTO locationDTO);

    public void flagInfection(long survivorId);

    public List<Survivor> findAllInfectedSurvivor();

    public List<Survivor> findAllNonInfectedSurvivor();



}
