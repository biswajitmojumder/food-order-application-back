package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.repository.OrdersRepository;
import com.group.foodorderapplicationback.repository.UserRepository;
import lombok.Getter;
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
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;

    @Override
    public List<Orders> findAll() {
        return (List<Orders>) ordersRepository.findAll();
    }

    @Override
    public List<Orders> findByOrderStatus(OrderStatus orderStatus) {
        return ordersRepository.findByOrderStatus(orderStatus);
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
}
