package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne
    private Address address;

}
