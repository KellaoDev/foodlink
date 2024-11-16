package com.foodlink.controller.dashboard;

import com.foodlink.entity.DonationEntity;
import com.foodlink.service.DonationService;
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
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public String exibirMenu(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("name", principal.getName());
        } else {
            model.addAttribute("name", "Não autenticado");
        }
        return "menu/menu";
    }

    @GetMapping("/statistics/{nameRestaurant}")
    public String getDonationStatistics(@PathVariable("nameRestaurant") String nameRestaurant, Model model) {

        List<DonationEntity> restaurantOp = donationService.findByName(nameRestaurant);

        if(restaurantOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }

        DonationEntity donation = restaurantOp.get(0);
        int totalDonations = donationService.getTotalDonationsByRestaurant(nameRestaurant);

        model.addAttribute("restaurant", donation);
        model.addAttribute("nameRestaurant", nameRestaurant);
        model.addAttribute("totalDonations", totalDonations);

        return "statistics/statisticsPage";
    }
}
