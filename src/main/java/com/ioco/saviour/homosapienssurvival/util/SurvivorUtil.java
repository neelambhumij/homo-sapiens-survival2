package com.ioco.saviour.homosapienssurvival.util;

import com.ioco.saviour.homosapienssurvival.dto.SurvivorDTO;
import com.ioco.saviour.homosapienssurvival.entity.Inventory;
import com.ioco.saviour.homosapienssurvival.entity.Survivor;

public class SurvivorUtil {

    public static Survivor populateSurvivor(SurvivorDTO survivorDTO){
        Inventory inventory = new Inventory(survivorDTO.getInventoryDTO().isWater(), survivorDTO.getInventoryDTO().isFood(), survivorDTO.getInventoryDTO().isMedication(), survivorDTO.getInventoryDTO().isAmmunition());
        Survivor survivor = new Survivor(survivorDTO.getName(), survivorDTO.getAge(), survivorDTO.getGender().name(), survivorDTO.getLocation());
        survivor.setInventory(inventory);
        inventory.setSurvivor(survivor);
        return survivor;
    }
}
