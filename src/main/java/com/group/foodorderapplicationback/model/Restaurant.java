package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @ManyToMany
    @JoinTable(name = "restaurant_food",
            joinColumns =@JoinColumn(name="restaurant_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="food_id",referencedColumnName="id"))
    private List<Food> foodList;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
