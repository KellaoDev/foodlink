package com.foodlink.controller.dashboard;

import com.foodlink.entity.DonationEntity;
import com.foodlink.entity.UserEntity;
import com.foodlink.service.DonationService;
import com.foodlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String exibirMenu(Model model, Principal principal) {

        if (principal != null) {
            String cnpj = principal.getName();
            UserEntity user = userService.findByCnpj(cnpj);

            if (user != null) {
                int totalDonations = donationService.getTotalDonationsByUser(user);
                model.addAttribute("totalDonations", totalDonations);
                model.addAttribute("user", user);
            }
        } else {
            model.addAttribute("name", "Não autenticado");
        }
        return "menu/menu";
    }


    @GetMapping("/statistics")
    public String getDonationStatistics(Model model, Principal principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
        }

        String cnpj = principal.getName();
        UserEntity user = userService.findByCnpj(cnpj);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        int totalDonations = donationService.getTotalDonationsByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("totalDonations", totalDonations);

        return "statistics/statisticsPage";
    }
}
