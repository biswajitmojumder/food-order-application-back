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

    @OneToOne(mappedBy = "deliveryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Orders activeOrder;

}
