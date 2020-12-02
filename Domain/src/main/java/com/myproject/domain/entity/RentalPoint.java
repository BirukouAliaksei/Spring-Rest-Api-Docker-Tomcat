package com.myproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rental_point")
public class RentalPoint {


    //@OneToMany(mappedBy ="scooter",cascade = CascadeType.ALL, orphanRemoval = true)
//    private Scooter scooter;
//    @OneToMany(mappedBy = "rentalPoint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<History> histories = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "parent_id")
    private int parentId;
    @Column(name = "latitude")
    private int latitude;
    @Column(name = "longitude")
    private int longitude;

    public RentalPoint() {
    }

    public RentalPoint(int id, String address, int parentId, int latitude, int longitude) {
        this.id = id;
        this.address = address;
        this.parentId = parentId;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

}
