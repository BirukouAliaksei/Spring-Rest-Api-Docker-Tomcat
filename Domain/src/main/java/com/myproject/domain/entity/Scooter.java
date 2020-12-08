package com.myproject.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scooters")
public class Scooter {

    @OneToMany(mappedBy = "scooter", fetch = FetchType.EAGER)
    private Set<History> histories;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "model")
    private String model;
    @Column(name = "availability")
    private boolean availability;
    @Column(name = "battery")
    private int battery;
    @Column(name = "rental_point_id")
    private int rentalPointId;

}
