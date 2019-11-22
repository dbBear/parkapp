package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping({"/categories"})
@Controller
public class CategoryController {

  private final CategoryService categoryService;
  private final ParkService parkService;

  public CategoryController(CategoryService categoryService, ParkService parkService) {
    this.categoryService = categoryService;
    this.parkService = parkService;
  }

  @GetMapping({"","/"})
  public String listCategories(Model model) {
    model.addAttribute("categories", categoryService.getCategories());
    return "backEndStuff/categories/categoryList";
  }

  @GetMapping("/new")
  public String newCategory(Model model) {
    Category c = new Category();
    model.addAttribute("category", c);
    return "backEndStuff/categories/categoryForm";
  }

  @PostMapping("/categories/new")
  public String saveOrUpdateCategory(
      @ModelAttribute Category category,
      Model model)
  {
    Category savedCategory =
        categoryService.saveCategory(category);
    return "redirect:/categories";
  }
}

