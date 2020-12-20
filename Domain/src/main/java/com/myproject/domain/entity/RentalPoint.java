package com.myproject.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental_point")
public class RentalPoint {

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY)
    private Set<Scooter> scooters;

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
}