package com.ioco.saviour.homosapienssurvival.repository;

import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurvivorRepository extends JpaRepository<Survivor,Long> {

    @Modifying
    @Query("update Survivor s set s.location = ?1 where s.id = ?2")
    void updateLocation(String location, long survivor_id);

    @Query("Select s from Survivor s where s.id in ?1")
    public List<Survivor> findAllInfectedRecords(List<Long> ids);

    @Query("Select s from Survivor s where s.id not in ?1")
    public List<Survivor> findAllNonInfectedRecords(List<Long> ids);
}
