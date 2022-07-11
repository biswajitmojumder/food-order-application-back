package com.group.foodorderapplicationback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
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
    private DeliveryUser activeDeliveryUser;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "orderHistory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DeliveryUser> deliveryUserHistory;

    @OneToOne
    private Address address;

}
