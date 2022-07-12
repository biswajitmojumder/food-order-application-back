package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
