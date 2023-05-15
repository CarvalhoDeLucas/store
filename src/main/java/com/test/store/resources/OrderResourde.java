package com.test.store.resources;

import com.test.store.entities.Item;
import com.test.store.entities.Order;
import com.test.store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
public class OrderResourde {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<List<Order>> findAll(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.findAll(pageable);
        return ResponseEntity.ok().body(orders.getContent());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Order>> findById(@PathVariable UUID id) {
        Optional<Order> order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        order = orderService.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (orderService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Order order) {
        if (orderService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        order = orderService.update(id, order);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/{discount}")
    public ResponseEntity<Void> discount(@PathVariable UUID id, @PathVariable int discount) {
        if (orderService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderService.discount(id, discount);
        return ResponseEntity.ok().build();
    }

}
