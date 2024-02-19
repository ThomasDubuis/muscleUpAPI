package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isEmpty()) {
            return categoryRepository.save(category);
        }else {
            logger.warn("Category already exist : {}", category.getName());
            return categoryOptional.get();
        }
    }

    public Category deleteCategory(IdRequest request) {
        Optional<Category> categoryOptional = categoryRepository.findById(request.getId());
        if (categoryOptional.isPresent()) {
            categoryRepository.delete(categoryOptional.get());
            return categoryOptional.get();
        }else {
            logger.error("Id does not exist in Category");
            return null;
        }
    }

    public Category updateCategory(Category request) {
        Optional<Category> categoryOptional = categoryRepository.findById(request.getId());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.updateIfNotNull(request);
            return categoryRepository.save(category);
        }else {
            logger.error("Id does not exist in Category");
            return null;
        }
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
