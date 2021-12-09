package com.ioco.saviour.homosapienssurvival.repository;

import com.ioco.saviour.homosapienssurvival.entity.InfectionRecords;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InfectionRecordRepository extends JpaRepository<InfectionRecords,Long> {

    public Optional<InfectionRecords> findById(Long id);
}
