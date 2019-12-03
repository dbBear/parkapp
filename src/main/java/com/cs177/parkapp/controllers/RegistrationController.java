package com.cs177.parkapp.controllers;

import com.cs177.parkapp.security.dto.UserDto;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.RoleService;
import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cs177.parkapp.config.StaticStuff.DEV_DIR;

@AllArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {

  private RoleService roleService;
  private UserService userService;

  @ModelAttribute("user")
  public UserDto userDto() {
    return new UserDto();
  }

  @GetMapping
  public String showRegistrationForm(Model model) {
    model.addAttribute("role", roleService.getRoleByShortName("user"));
    return DEV_DIR + "/registration/registration-form";
  }

  @PostMapping
  public String registerNewUser(
      @ModelAttribute("user") UserDto userDto,
      BindingResult result
  ){
    User userExisting = userService.findByEmail(userDto.getEmail());
    if(userExisting != null) {
      result.rejectValue("email", "There is already an account registered " +
          "with this email.");
    }
    if(result.hasErrors()) {
      return DEV_DIR + "/registration/registration-form";
    }
    userService.save(userDto);
    return "redirect:/";
  }
}
