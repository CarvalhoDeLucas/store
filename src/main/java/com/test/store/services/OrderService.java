package com.test.store.services;

import com.test.store.entities.Order;
import com.test.store.entities.OrderItem;
import com.test.store.repositories.OrderItemRepository;
import com.test.store.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Order order = orderRepository.getById(id);
        order.setDeleted(true);
        orderRepository.save(order);
    }

    public void discount(UUID id, int discount) {
        List<OrderItem> orderItemList = orderItemRepository.findBYOrder(id);

        double value = 0;

        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getItem().getCategories().getTypeItem().toUpperCase().equals("PRODUCT")) {
                value += orderItem.getItem().getPrice();
            }
        }

        Order order = orderRepository.getById(id);
        order.setTotalValue(order.getTotalValue() - value + (value * (discount / 100)));
        orderRepository.save(order);
    }

    public Order update(UUID id, Order data) {
        Order order = orderRepository.getById(id);
        updateData(order, data);
        return orderRepository.save(order);
    }

    private void updateData(Order order, Order data) {
        if (!data.getSituation().isEmpty()) {
            order.setSituation(data.getSituation());
        }
    }

}
