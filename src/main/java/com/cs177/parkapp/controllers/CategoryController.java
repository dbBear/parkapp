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
    model.addAttribute("category", new Category());
    return "backEndStuff/categories/categoryForm";
  }

  @PostMapping("")
  public String saveOrUpdateCategory(
      @ModelAttribute Category category,
      Model model)
  {
    Category savedCategory =
        categoryService.save(category);
    return "redirect:/categories";
  }

  @GetMapping("/update")
  public String updateCategory(
      @RequestParam String id,
      Model model
  ) {
    Category category = categoryService.findById(Long.valueOf(id));
    model.addAttribute("category", category);
    return "backEndStuff/categories/categoryForm";
  }

  @GetMapping("/delete")
  public String deleteCategory(
      @RequestParam String id,
      Model model
  ) {

    Category category = categoryService.findById(Long.valueOf(id));
    categoryService.delete(category);
    return "redirect:/categories";
  }
}

