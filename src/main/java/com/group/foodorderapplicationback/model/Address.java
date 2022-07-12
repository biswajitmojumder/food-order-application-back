package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String number;

    @OneToOne(mappedBy = "address")
    private Restaurant restaurant;

    @OneToOne(mappedBy = "address")
    private Orders order;

}
