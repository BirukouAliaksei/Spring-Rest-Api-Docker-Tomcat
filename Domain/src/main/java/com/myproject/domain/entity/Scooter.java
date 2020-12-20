package com.myproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scooters")
public class Scooter {

    @OneToMany(mappedBy = "scooter", fetch = FetchType.LAZY)
    private Set<History> histories;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "rental_point_id",insertable = false, updatable = false)
    @JsonIgnore
    private RentalPoint point;

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
