package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
    List<User> findByUsernameContainingOrEmailContaining(String username, String email);
}
