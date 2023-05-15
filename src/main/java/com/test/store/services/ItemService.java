package com.test.store.services;

import com.test.store.entities.Item;
import com.test.store.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    public Item update(UUID id, Item data) {
        Item item = itemRepository.getById(id);
        updateData(item, data);
        return itemRepository.save(item);
    }

    private void updateData(Item item, Item data) {
        if (!data.getNameItem().isEmpty()) {
            item.setNameItem(data.getNameItem());
        }

        if (!data.getDescriptionItem().isEmpty()) {
            item.setDescriptionItem(data.getDescriptionItem());
        }

        if (data.getCategories().getId() != null) {
            item.setCategories(data.getCategories());
        }

        if (data.getPrice() == 0) {
            item.setPrice(data.getPrice());
        }
    }

}
