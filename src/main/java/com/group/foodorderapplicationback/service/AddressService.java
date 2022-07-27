package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Address;

import java.util.Optional;

public interface AddressService {

    Optional<Address> findByStreetAddress(String streetAddress);

    Address save(Address address);

}
