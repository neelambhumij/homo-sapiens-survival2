package com.ioco.saviour.homosapienssurvival.repository;

import com.ioco.saviour.homosapienssurvival.entity.Inventory;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorInventoryRepository extends JpaRepository<Inventory,Long> {
}
