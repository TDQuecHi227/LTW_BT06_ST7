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
        return categoryRepository.findAll();
    }
}
