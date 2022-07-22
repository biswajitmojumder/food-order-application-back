package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
//    Account saveAccount(Account account);
//    Role saveRole(Role role);
//    void addRoleToAccount(String username, String roleName);
//    Account getAccount(String username);
//    List<Account> getAccounts();  //Use pagination
    List<User> findAll();
    User saveUser(User user);
    User getUser(String username);
    void deleteById(Long id);
    Food addFoodToFavorites(Long foodId, HttpServletRequest request);
    void removeFoodFromFavourites(Long id, HttpServletRequest request);
    List<Food> findFavouriteFoodForUser(HttpServletRequest request);
    List<Orders> findAllOrdersForUser(HttpServletRequest request);
    User getUserInfo(HttpServletRequest request);
    Page<User> findAll(Pageable pageable);
}
