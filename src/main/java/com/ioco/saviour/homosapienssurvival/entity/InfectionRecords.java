package com.ioco.saviour.homosapienssurvival.entity;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "infection_records")
public class InfectionRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long survivor_id;

    private int times_reported;

    private boolean flag;


    public InfectionRecords(long survivor_id, int times_reported, boolean flag) {
        this.flag = flag;
        this.times_reported = times_reported;
        this.survivor_id = survivor_id;
    }
}
