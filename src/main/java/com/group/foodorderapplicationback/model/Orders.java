package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private DeliveryUser deliveryUser;

    @ManyToMany
    @JoinTable(name = "orders_food",
        joinColumns =@JoinColumn(name="order_id",referencedColumnName = "id"),
        inverseJoinColumns =@JoinColumn(name="food_id",referencedColumnName="id"))
    private List<Food> foodList;

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private Address address;

}
