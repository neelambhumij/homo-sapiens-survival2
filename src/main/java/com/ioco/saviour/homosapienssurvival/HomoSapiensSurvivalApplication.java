package com.ioco.saviour.homosapienssurvival;

import com.ioco.saviour.homosapienssurvival.repository.InfectionRecordRepository;
import com.ioco.saviour.homosapienssurvival.repository.SurvivorInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomoSapiensSurvivalApplication implements CommandLineRunner {

	@Autowired
	private SurvivorInventoryRepository survivorRepository;

	@Autowired
	private InfectionRecordRepository infectionRecordRepository;


	public static void main(String[] args) {
		SpringApplication.run(HomoSapiensSurvivalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Inventory i = new Inventory();
//		i.setAmmunition(true);
//		i.setMedication(true);
//		i.setFood(true);
//		i.setWater(true);
//		Survivor s = new Survivor("Neelam", 30, Gender.Female.name(), "Bolangir,Badmal");
//		i.setSurvivor(s);
//		s.setInventory(i);
//
//		Survivor save = survivorRepository.save(s);

//		InfectionRecords if1 = new InfectionRecords(save.getId(), 3, true);
//		infectionRecordRepository.save(if1);
//
//		SurvivorDTO survivorDTO = new SurvivorDTO();
//		modelMapper.map(s,survivorDTO);
//		System.out.printf(survivorDTO.toString());


	}
}
