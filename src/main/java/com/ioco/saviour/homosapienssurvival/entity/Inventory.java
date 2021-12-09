package com.ioco.saviour.homosapienssurvival.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invent_id")
    private long id;

    private boolean water;

    private boolean food;

    private boolean medication;

    private boolean ammunition;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "survivor_id")

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "survivor_id")
    @JsonIgnore
    private Survivor survivor;

    public Inventory(boolean water, boolean food, boolean medication, boolean ammunition) {
        this.water = water;
        this.food = food;
        this.medication = medication;
        this.ammunition = ammunition;
    }
}
