package com.myproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class History {

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "scooter_id", insertable = false, updatable = false)
    @JsonIgnore
    private Scooter scooter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "mileage")
    private Double mileage;
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
    public final static String discount = "discount";

}