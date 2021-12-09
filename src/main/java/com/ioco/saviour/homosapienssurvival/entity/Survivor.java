package com.ioco.saviour.homosapienssurvival.entity;

import com.ioco.saviour.homosapienssurvival.Gender;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survivor_id")
    private long id;

    private String name;

    private int age;

    private String gender;

    private String location;

    @OneToOne(mappedBy = "survivor", fetch = FetchType.LAZY)
    private Inventory inventory;

    public Survivor(String name, int age, String gender, String location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }
}
