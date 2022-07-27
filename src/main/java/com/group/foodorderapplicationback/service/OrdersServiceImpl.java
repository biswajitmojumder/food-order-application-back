package com.group.foodorderapplicationback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group.foodorderapplicationback.model.*;
import com.group.foodorderapplicationback.repository.AddressRepository;
import com.group.foodorderapplicationback.repository.FoodRepository;
import com.group.foodorderapplicationback.repository.OrdersRepository;
import com.group.foodorderapplicationback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Orders> findAll() {
        return (List<Orders>) ordersRepository.findAll();
    }

    @Override
    public List<Orders> findAllByOrderStatus(OrderStatus orderStatus) {
        return ordersRepository.findAllByOrderStatus(orderStatus);
    }

    @Override
    public Orders insertOrder(Long userId, Orders order) {
        log.info("Searching user with id {" + userId + "} ...");
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            Orders newOrder = new Orders();

            newOrder.setUser(user.get());
            newOrder.setOrderStatus(OrderStatus.RECEIVED);

            return ordersRepository.save(newOrder);
        }
        else {
            throw new RuntimeException("User id not found!");
        }
    }

    @Override
    public Orders insertOrderForAuthenticatedUser(HttpServletRequest request, Orders order) {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        log.info("Inserting order for user: {" + decodedJWT.getSubject() + "}");

        User user = userRepository.findByUsername(decodedJWT.getSubject());

        Orders newOrder = new Orders();
        newOrder.setUser(user);
        newOrder.setOrderStatus(OrderStatus.RECEIVED);
        newOrder.setOrderDateTime(LocalDateTime.now());

        List<Food> foodList = new ArrayList<>();

        for(int i=0; i<order.getFoodList().size(); i++) {
            Food food = foodRepository.findById(order.getFoodList().get(i).getId()).get();
            foodList.add(food);
        }

        newOrder.setFoodList(foodList);

        Optional<Address> newAddress = addressRepository.findByStreetAddress(order.getAddress().getStreetAddress());

        if(newAddress.isPresent()) {
            newOrder.setAddress(newAddress.get());
        }
        else {
            Address address = Address.builder()
                .streetAddress(order.getAddress().getStreetAddress())
                .city(order.getAddress().getCity())
                .zipCode(order.getAddress().getZipCode())
                .build();

            addressRepository.save(address);
            newOrder.setAddress(address);
        }

        return ordersRepository.save(newOrder);
    }

    @Override
    public Orders nextStatus(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        String orderStatusVal = order.getOrderStatus().name();

        if(orderStatusVal.equals("REJECTED") || orderStatusVal.equals("DELIVERED")) {
            throw new RuntimeException("This order cannot be updated anymore!");
        }

        if(order.getOrderStatus().ordinal() < OrderStatus.values().length-2) {
            OrderStatus orderStatus = OrderStatus.values()[order.getOrderStatus().ordinal() + 1];
            order.setOrderStatus(orderStatus);
        }

        return order;
    }

    @Override
    public Orders setAcceptedStatus(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.ACCEPTED);

        return order;
    }

    @Override
    public Orders setPreparingStatus(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.PREPARING);

        return order;
    }

    @Override
    public Orders setOnTheWay(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.ON_THE_WAY);

        return order;
    }

    @Override
    public Orders setDeliveredStatus(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.DELIVERED);

        return order;
    }

    @Override
    public Orders setRejectedStatus(Long orderId) {
        Orders order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.REJECTED);

        return order;
    }


}
