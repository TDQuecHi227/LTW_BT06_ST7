package com.bt06.bt06.service;

import com.bt06.bt06.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    void saveCategory(Category category);
    void deleteCategory(Category category);
    Category getCategoryById(long id);
    Category getCategoryByName(String name);
    List<Category> searchByName(String name);
}
