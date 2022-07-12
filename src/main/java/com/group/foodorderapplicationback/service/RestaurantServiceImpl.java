package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Restaurant;
import com.group.foodorderapplicationback.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
