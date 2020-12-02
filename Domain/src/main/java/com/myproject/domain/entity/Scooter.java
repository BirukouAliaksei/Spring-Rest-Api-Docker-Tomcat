package com.myproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "scooters")
public class Scooter {

//    @OneToMany(mappedBy = "scooter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<History> histories = new ArrayList<>();


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

    public Scooter() {
    }

    public Scooter(int id, Double cost, String model, boolean availability, int battery, int rentalPointId) {
        this.id = id;
        this.cost = cost;
        this.model = model;
        this.availability = availability;
        this.battery = battery;
        this.rentalPointId = rentalPointId;
    }

//    @JsonIgnore
//    public List<History> getHistories() {
//        return histories;
//    }
//
//    @JsonIgnore
//    public void setHistories(List<History> histories) {
//        this.histories = histories;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getRentalPointId() {
        return rentalPointId;
    }

    public void setRentalPointId(int rentalPointId) {
        this.rentalPointId = rentalPointId;
    }

}
