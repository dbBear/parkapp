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

import javax.validation.Valid;

import static com.cs177.parkapp.config.StaticNames.*;

@AllArgsConstructor
@Controller
@RequestMapping("/new-user")
public class RegistrationController {

  private final RoleService roleService;
  private final UserService userService;

  @ModelAttribute("user")
  public UserDto userDto() {
    return new UserDto();
  }

  @GetMapping({"","/"})
  public String showRegistrationForm(Model model) {
    return DEV_DIR + "/registration/registration-form";
  }

  @PostMapping({"","/"})
  public String registerNewUser(
      @ModelAttribute("user") @Valid UserDto userDto,
      BindingResult result
  ){
    User userExisting = userService.findByEmail(userDto.getEmail());
    if(userExisting.getId() != null) {
      result.rejectValue("email", "There is already an account registered " +
          "with this email.");
    }
    if(result.hasErrors()) {
      return DEV_DIR + "/registration/registration-form";
    }
    userDto.setRoleId(roleService.findByName(ROLE_USER).getId());
    userService.save(userDto);
    return "redirect:/";
  }
}
