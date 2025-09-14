package com.bt06.bt06.service.impl;

import com.bt06.bt06.entity.Category;
import com.bt06.bt06.repository.CategoryRepository;
import com.bt06.bt06.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAllBy();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCateName(name);
    }

    @Override
    public List<Category> searchByName(String keyword) {
        return categoryRepository.findByCateNameContainingIgnoreCase(keyword);
    }

}
