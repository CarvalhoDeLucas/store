package com.test.store.resources;

import com.test.store.entities.Category;
import com.test.store.entities.Item;
import com.test.store.services.CategoryService;
import com.test.store.services.ItemService;
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
@RequestMapping(value = "/items")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<List<Item>> findAll(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Item> items = itemService.findAll(pageable);
        return ResponseEntity.ok().body(items.getContent());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Item>> findById(@PathVariable UUID id) {
        Optional<Item> item = itemService.findById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Item item) {
        if (categoryService.findById(item.getCategories().getId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Categoria nao encontrada");
        }

        item = itemService.insert(item);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (itemService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Item item) {
        if (itemService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        item = itemService.update(id, item);
        return ResponseEntity.ok().build();
    }


}
