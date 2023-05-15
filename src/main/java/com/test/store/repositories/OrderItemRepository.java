package com.test.store.repositories;

import com.test.store.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query(value = "select * from order_item where order_id = ?1", nativeQuery = true)
    List<OrderItem> findBYOrder(@Param("id") UUID id);

}
