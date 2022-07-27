package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByStreetAddress(String streetAddress);
}
