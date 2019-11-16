package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping({"/admin"})
@Controller
public class AdminController {

  private final CategoryService categoryService;
  private final ParkService parkService;

  public AdminController(CategoryService categoryService, ParkService parkService) {
    this.categoryService = categoryService;
    this.parkService = parkService;
  }

  @GetMapping("/categories")
  public String listCategories(Model model) {
    model.addAttribute("categories", categoryService.getCategories());
    return "backEndStuff/admin/categories/list";
  }

  @GetMapping("/categories/new")
  public String newCategory(Model model) {
    Category c = new Category();
    model.addAttribute("category", c);
    return "backEndStuff/admin/categories/categoryForm";
  }

  @PostMapping("/categories")
  public String saveOrUpdateCategory(
      @ModelAttribute Category category,
      Model model)
  {
    Category savedCategory =
        categoryService.saveCategory(category);
    return "redirect:/admin/categories";
  }
}
