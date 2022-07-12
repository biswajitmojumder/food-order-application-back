package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;
    private String comment;

    @ManyToOne
    private Food food;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}
