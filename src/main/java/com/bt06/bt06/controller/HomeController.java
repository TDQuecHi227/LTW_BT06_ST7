package com.bt06.bt06.controller;

import com.bt06.bt06.entity.Category;
import com.bt06.bt06.service.CategoryService;
import com.bt06.bt06.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/categories")
public class HomeController {
    private final CategoryService  categoryService;
    @GetMapping("")
    public String getHome(Model model){
        List<Category> categoryList = categoryService.getCategories();
        model.addAttribute("categories",categoryList);
        return "category";
    }
}
