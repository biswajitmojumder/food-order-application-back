package com.group.foodorderapplicationback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class DeliveryUser extends Account {

    private String vehicleManufacturer;
    private String vehicleNumber;
    private String vehicleColor;
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "deliveryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> allOrders;

}
