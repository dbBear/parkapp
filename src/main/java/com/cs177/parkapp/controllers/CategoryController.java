package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.cs177.parkapp.config.StaticStuff.DEV_DIR;

@AllArgsConstructor
@Controller
@RequestMapping({"/categories"})
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping({"","/"})
  public String listCategories(Model model) {
    model.addAttribute("categories", categoryService.getCategories());
    return DEV_DIR + "/categories/categoryList";
  }

  @GetMapping("/new")
  public String newCategory(Model model) {
    model.addAttribute("category", new Category());
    return DEV_DIR + "/categories/categoryForm";
  }

  @GetMapping("/update")
  public String updateCategory(
      @RequestParam(required = false) String id,
      Model model
  ) {
    Category category;
    if(id != null) {
      category = categoryService.findById(Long.valueOf(id));
    } else {
      category = new Category();
    }
    model.addAttribute("category", category);
    return DEV_DIR + "/categories/categoryForm";
  }

  @PostMapping("")
  public String saveCategory(
      @ModelAttribute Category category,
      Model model)
  {
    Category savedCategory =
        categoryService.save(category);
    return "redirect:/categories";
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

