package com.myproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "history")
public class History {

//    @Transient
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", insertable = false, nullable = false)
//    private User user;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "scooter_id")
//    private Scooter scooter;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "start_location_id")
//    private RentalPoint rentalPoint;


    @Id
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "mileage")
    private Double mileade;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    @Column(name = "start_location_id")
    private int startLocationId;
    @Column(name = "finish_location_id")
    private int finishLocationId;
    @Column(name = "offer_type")
    private String offerType;
    @Column(name = "offer_cost")
    private Double offerCost;
    @Column(name = "scooter_id")
    private int scooterId;
    @Transient
    //FIXME final static
    private String discount = "discount";

    public History() {
    }

    public History(int userId, Double mileade, LocalDateTime startTime, LocalDateTime finishTime, int startLocationId, int finishLocationId, String offerType, Double offerCost, int scooterId, String discount) {
        this.userId = userId;
        this.mileade = mileade;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startLocationId = startLocationId;
        this.finishLocationId = finishLocationId;
        this.offerType = offerType;
        this.offerCost = offerCost;
        this.scooterId = scooterId;
        this.discount = discount;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getMileade() {
        return mileade;
    }

    public void setMileade(Double mileade) {
        this.mileade = mileade;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public int getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(int startLocationId) {
        this.startLocationId = startLocationId;
    }

    public int getFinishLocationId() {
        return finishLocationId;
    }

    public void setFinishLocationId(int finishLocationId) {
        this.finishLocationId = finishLocationId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Double getOfferCost() {
        return offerCost;
    }

    public void setOfferCost(Double offerCost) {
        this.offerCost = offerCost;
    }

    public int getScooterId() {
        return scooterId;
    }

    public void setScooterId(int scooterId) {
        this.scooterId = scooterId;
    }
}
