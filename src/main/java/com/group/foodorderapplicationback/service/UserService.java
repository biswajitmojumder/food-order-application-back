package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User saveUser(User user);
    void deleteById(Long id);
    Food addFoodToFavorites(Long foodId, HttpServletRequest request);
    void removeFoodFromFavourites(Long id, HttpServletRequest request);
    List<Food> findFavouriteFoodForUser(HttpServletRequest request);
    List<Orders> findAllOrdersForUser(HttpServletRequest request);
    User getUserInfo(HttpServletRequest request);
    Page<User> findAll(Pageable pageable);
    List<User> searchByUsernameOrEmail(String username, String email);
    User update(HttpServletRequest request, User user);
}
