package com.ioco.saviour.homosapienssurvival.controller;

import com.ioco.saviour.homosapienssurvival.Gender;
import com.ioco.saviour.homosapienssurvival.dto.InventoryDTO;
import com.ioco.saviour.homosapienssurvival.dto.LocationDTO;
import com.ioco.saviour.homosapienssurvival.dto.SurvivorDTO;
import com.ioco.saviour.homosapienssurvival.service.SurvivorService;
import com.ioco.saviour.homosapienssurvival.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurvivorController.class)
class SurvivorControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    SurvivorService survivorService;

    SurvivorDTO survivorDTO = new SurvivorDTO();

    @BeforeEach
    void setUp() {
        survivorDTO.setName("Sansa");
        survivorDTO.setAge(10);
        survivorDTO.setGender(Gender.Female);
        survivorDTO.setLatitude("latitude");
        survivorDTO.setLongitude("longitude");
        survivorDTO.setInventoryDTO(new InventoryDTO(true, true, true, true));
    }

    @Test
    void createSurvivor() throws Exception {
        mvc.perform(post("/api/survivor/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(survivorDTO))).andExpect(status().isCreated());
        verify(survivorService, VerificationModeFactory.times(1)).createSurvivor(Mockito.any());
        reset(survivorService);
    }

    @Test
    void updateSurvivorLocation() throws Exception {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setSurvivorId(1);
        locationDTO.setLongitude("longitude");
        locationDTO.setLatitude("latitude");
        mvc.perform(post("/api/survivor/updateLocation").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(locationDTO))).andExpect(status().is2xxSuccessful());
        verify(survivorService, VerificationModeFactory.times(1)).updateSurvivorLocation(Mockito.any());
        reset(survivorService);
    }

    @Test
    void flagInfected() throws Exception {
        long survivorId = 1;
        mvc.perform(post("/api/survivor/flagInfected").contentType(MediaType.APPLICATION_JSON_VALUE).content(JsonUtil.toJson(survivorId))).andExpect(status().is2xxSuccessful());
        verify(survivorService, VerificationModeFactory.times(1)).flagInfection(survivorId);
        reset(survivorService);
    }

    @Test
    void fectchInfectedSurvivors() throws Exception {
        mvc.perform(get("/api/survivor/fetchInfectedSurvivors").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful());
        verify(survivorService, VerificationModeFactory.times(1)).findAllInfectedSurvivor();
        reset(survivorService);
    }
}