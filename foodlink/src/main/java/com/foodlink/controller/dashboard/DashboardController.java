package com.foodlink.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String exibirMenu(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("name", principal.getName());
        } else {
            model.addAttribute("name", "Não autenticado");
        }
        return "menu/menu";
    }

}
