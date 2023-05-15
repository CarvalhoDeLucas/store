package com.test.store.services;

import com.test.store.entities.OrderItem;
import com.test.store.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    public Optional<OrderItem> findById(UUID id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem insert(OrderItem orderItem) {
        orderItem.setTotalValue(orderItem.getQuantity() * orderItem.getItem().getPrice());
        return orderItemRepository.save(orderItem);
    }

    public void delete(UUID id) {
        orderItemRepository.deleteById(id);
    }

}
