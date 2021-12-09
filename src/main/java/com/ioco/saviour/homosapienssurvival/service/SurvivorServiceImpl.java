package com.ioco.saviour.homosapienssurvival.service;

import com.ioco.saviour.homosapienssurvival.dto.LocationDTO;
import com.ioco.saviour.homosapienssurvival.entity.InfectionRecords;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import com.ioco.saviour.homosapienssurvival.repository.InfectionRecordRepository;
import com.ioco.saviour.homosapienssurvival.repository.SurvivorInventoryRepository;
import com.ioco.saviour.homosapienssurvival.repository.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SurvivorServiceImpl implements SurvivorService {

    @Autowired
    private SurvivorInventoryRepository survivorInventoryRepository;

    @Autowired
    private SurvivorRepository survivorRepository;

    @Autowired
    private InfectionRecordRepository infectionRecordRepository;

    public void createSurvivor(Survivor survivor) {
        survivorInventoryRepository.save(survivor.getInventory());
    }

    @Override
    public void updateSurvivorLocation(LocationDTO locationDTO) {
        Optional<Survivor> existingSurvivor = survivorRepository.findById(locationDTO.getSurvivorId());
        if (existingSurvivor.isPresent()) {
            survivorRepository.updateLocation(locationDTO.getLatitude() + "," + locationDTO.getLongitude(), locationDTO.getSurvivorId());
        }
    }

    @Override
    public void flagInfection(long survivorId) {
        Optional<InfectionRecords> infectionRecord = infectionRecordRepository.findById(survivorId);
        if (!infectionRecord.isPresent()) {
            infectionRecordRepository.save(new InfectionRecords(survivorId, 1, false));
        } else {
            int times_reported = infectionRecord.get().getTimes_reported();
            if (times_reported < 3) {
                infectionRecord.get().setTimes_reported(times_reported++);
                if (times_reported == 3) {
                    infectionRecord.get().setFlag(true);
                }
                infectionRecord.get().setTimes_reported(times_reported);
                infectionRecordRepository.save(infectionRecord.get());
            }
        }

    }

    @Override
    public List<Survivor> findAllInfectedSurvivor() {
        return survivorRepository.findAllInfectedRecords(getInfectesIds());

    }

    @Override
    public List<Survivor> findAllNonInfectedSurvivor() {
        return survivorRepository.findAllNonInfectedRecords(getInfectesIds());
    }

    private List<Long> getInfectesIds(){
        List<InfectionRecords> allRecords = infectionRecordRepository.findAll();
        List<Long> infectedIds = allRecords.stream()
                .filter(record -> record.isFlag() == true)
                .map(InfectionRecords::getSurvivor_id)
                .collect(Collectors.toList());
        return infectedIds;
    }
}
