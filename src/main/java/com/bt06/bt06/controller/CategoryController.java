package com.bt06.bt06.controller;

import com.bt06.bt06.entity.Category;
import com.bt06.bt06.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService  categoryService;
    @GetMapping("")
    public String getHome(@RequestParam(value = "q", required = false) String keyword, Model model){
        List<Category> categories;

        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = categoryService.searchByName(keyword.trim());
            model.addAttribute("q", keyword); // để hiển thị lại trong ô input
        } else {
            categories = categoryService.getCategories();
        }
        model.addAttribute("categories", categories);
        return "category/category";
    }
    @GetMapping("add")
    public String getAdd(Model model){return "category/add";}
    @PostMapping("add")
    public String postAdd(Model model, @RequestParam("cateName") String cateName, @RequestParam("cateIconFile") MultipartFile cateIcon) throws IOException {
        String fileName = null;
        if (!cateIcon.isEmpty()) {
            fileName = cateIcon.getOriginalFilename();
            Path uploadDir = Paths.get("images/category");
            Files.createDirectories(uploadDir);
            cateIcon.transferTo(uploadDir.resolve(fileName));
        }
        Category oldCategory = categoryService.getCategoryByName(cateName);
        if(oldCategory == null) {
            Category category = new Category();
            category.setCateName(cateName);
            category.setCateIcon(fileName);
            categoryService.saveCategory(category);
            return "redirect:/admin/categories";
        }
        else{
            // Gửi thông báo lỗi cho form add.html
            model.addAttribute("errorMessage", "Danh mục '" + cateName + "' đã tồn tại!");
            return "category/add";
        }
    }
    @GetMapping("delete/{id}")
    public String postDelete(Model model, @PathVariable("id") long id){
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("edit/{id}")
    public String getEdit(Model model, @PathVariable("id") long id){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }
    @PostMapping("edit/{id}")
    public String postEdit(Model model, @PathVariable("id") long id, @RequestParam("cateName")  String cateName, @RequestParam("cateIconFile") MultipartFile cateIcon) throws IOException {
        String fileName = null;
        if (!cateIcon.isEmpty()) {
            fileName = cateIcon.getOriginalFilename();
            Path uploadDir = Paths.get("images/category");
            Files.createDirectories(uploadDir);
            cateIcon.transferTo(uploadDir.resolve(fileName));
        }
        Category category = categoryService.getCategoryById(id);
        Category oldCategory = categoryService.getCategoryByName(cateName);
        if(oldCategory != null && !oldCategory.getCateName().equals(category.getCateName())) {
            model.addAttribute("errorMessage", "Danh mục '" + cateName + "' đã tồn tại!");
            model.addAttribute("category",category);
            return "category/edit";
        }else {
            category.setCateName(cateName);
            if (fileName != null && !fileName.isEmpty()) {
                category.setCateIcon(fileName);
            }
            categoryService.saveCategory(category);
            return "redirect:/admin/categories";
        }
    }
}
