package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Image findByName(String name);
}
