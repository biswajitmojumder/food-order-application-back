package com.group.foodorderapplicationback.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class DeliveryUser extends Account {

    private String vehicleManufacturer;
    private String vehicleNumber;
    private String vehicleColor;
    private String phoneNumber;

    @OneToOne(mappedBy = "deliveryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Orders activeOrder;

}
