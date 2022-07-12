package com.group.foodorderapplicationback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String weight;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "favouriteFood", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> user;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Review> foodReview;

    @ManyToMany(mappedBy = "foodList")
    private List<Restaurant> restaurantList;

    @ManyToMany(mappedBy = "foodList")
    private List<Orders> ordersList;

}
