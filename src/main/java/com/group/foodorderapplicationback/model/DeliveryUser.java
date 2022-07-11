package com.group.foodorderapplicationback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class DeliveryUser extends Account {

    private String vehicleManufacturer;
    private String vehicleNumber;
    private String vehicleColor;
    private String phoneNumber;

    @OneToOne(mappedBy = "activeDeliveryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Orders activeOrder;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "delivery_history",
            joinColumns =@JoinColumn(name="delivery_user_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="order_id",referencedColumnName="id"))
    private List<Orders> orderHistory;

}
