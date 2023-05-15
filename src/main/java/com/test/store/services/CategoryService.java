package com.test.store.services;

import com.test.store.entities.Category;
import com.test.store.entities.Item;
import com.test.store.repositories.CategoryRepository;
import com.test.store.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category insert(Category data) {
        return categoryRepository.save(data);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    public Category update(UUID id, Category data) {
        Category category = categoryRepository.getById(id);
        updateData(category, data);
        return categoryRepository.save(category);
    }

    private void updateData(Category category, Category data) {
        category.setTypeItem(data.getTypeItem());
    }

}
