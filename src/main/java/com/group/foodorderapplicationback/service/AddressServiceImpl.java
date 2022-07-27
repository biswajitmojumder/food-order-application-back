package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Address;
import com.group.foodorderapplicationback.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Optional<Address> findByStreetAddress(String streetAddress) {
        return addressRepository.findByStreetAddress(streetAddress);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

}
