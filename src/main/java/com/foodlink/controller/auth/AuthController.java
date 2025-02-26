package com.foodlink.controller.auth;

import com.foodlink.dto.UserRegistrationDTO;
import com.foodlink.entity.UserEntity;
import com.foodlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String exibirLogin(Model model) {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserRegistrationDTO userDto, Model model) {
        try {
            userService.loginUser(userDto);
            return "redirect:/dashboard";
        } catch (BadCredentialsException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }

    @GetMapping("/displayRegister")
    public String displayRegister(Model model) {
        model.addAttribute("user", new UserEntity());
        return "auth/displayRegister";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDTO userDto, Model model) {
        try {
            userService.registerUser(userDto);
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/displayRegister";
        }
    }

}
