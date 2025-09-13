package com.bt06.bt06.service;

import com.bt06.bt06.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
}
