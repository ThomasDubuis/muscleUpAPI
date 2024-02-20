package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    private final Category CATEGORY = new Category("1", "categoryName", "categoryDesc");

    @Test
    void addCategoryButCategoryAlreadyExist(CapturedOutput output) {
        when(categoryRepository.findByName("categoryName")).thenReturn(Optional.of(CATEGORY));

        Category response = categoryService.addCategory(CATEGORY);

        verify(categoryRepository, times(0)).save(any());
        assertEquals(CATEGORY, response);
        assertTrue(output.getOut().contains("Category already exist : categoryName"));
    }

    @Test
    void addCategoryShouldReturnCategory() {
        when(categoryRepository.findByName("categoryName")).thenReturn(Optional.empty());
        when(categoryRepository.save(CATEGORY)).thenReturn(CATEGORY);

        Category response = categoryService.addCategory(CATEGORY);

        verify(categoryRepository, times(1)).save(any());
        assertEquals(CATEGORY, response);
    }

    @Test
    void deleteCategoryButCategoryNotExist(CapturedOutput output) {
        IdRequest request = new IdRequest("1");
        when(categoryRepository.findById("1")).thenReturn(Optional.empty());

        Category response = categoryService.deleteCategory(request);

        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Category"));
        verify(categoryRepository, times(0)).delete(any());
    }

    @Test
    void deleteCategoryShouldDeleteCategory() {
        IdRequest request = new IdRequest("1");
        when(categoryRepository.findById("1")).thenReturn(Optional.of(CATEGORY));

        Category response = categoryService.deleteCategory(request);
        assertEquals(CATEGORY, response);
        verify(categoryRepository, times(1)).delete(any());
    }

    @Test
    void updateCategoryButCategoryNotExist(CapturedOutput output) {
        when(categoryRepository.findById("1")).thenReturn(Optional.empty());

        Category response = categoryService.updateCategory(CATEGORY);

        verify(categoryRepository, times(0)).save(any());
        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Category"));
    }

    @Test
    void updateCategoryShouldUpdateCategory() {
        when(categoryRepository.findById("1")).thenReturn(Optional.of(CATEGORY));
        when(categoryRepository.save(CATEGORY)).thenReturn(CATEGORY);

        Category response = categoryService.updateCategory(CATEGORY);

        verify(categoryRepository, times(1)).save(any());
        assertEquals(CATEGORY, response);
    }

    @Test
    void getCategoriesShouldReturnAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(CATEGORY));

        List<Category> response = categoryService.getCategories();

        assertEquals(CATEGORY, response.get(0));
    }
}