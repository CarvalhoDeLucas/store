package com.test.store.resources;

import com.test.store.entities.OrderItem;
import com.test.store.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orderItem")
public class OrderItemResourse {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<List<OrderItem>> findAll(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderItem> orderItems = orderItemService.findAll(pageable);
        return ResponseEntity.ok().body(orderItems.getContent());
    }

    @PostMapping
    public ResponseEntity<OrderItem> insert(@RequestBody OrderItem orderItem) {
        orderItem = orderItemService.insert(orderItem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderItem.getId()).toUri();
        return ResponseEntity.created(uri).body(orderItem);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (orderItemService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
