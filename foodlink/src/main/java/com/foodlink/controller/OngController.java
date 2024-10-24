package com.foodlink.controller;

import com.foodlink.entity.DonationEntity;
import com.foodlink.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/vizualizar-doacoes")
    public String getMyDonationsCarriedOut(Model model) {
        List<DonationEntity> availableDonations = donationService.getAvailableDonations();
        model.addAttribute("donation", availableDonations);
        return "/ong/vizualizarDoacoes";
    }

    @PostMapping("/accept")
    public String acceptDonation(@RequestParam("donationId") Long donationId) {
        donationService.acceptDonation(donationId);
        return "redirect:/ong/vizualizar-doacoes";
    }

    @GetMapping("/minhas-doacoes")
    public String getMyDonations(Model model) {
        return "/ong/minhasDoacoes";
    }

}
