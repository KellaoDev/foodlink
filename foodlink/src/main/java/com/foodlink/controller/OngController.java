package com.foodlink.controller;

import com.foodlink.entity.DonationEntity;
import com.foodlink.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/vizualizar-doacoes")
    public String getMyDonationsCarriedOut(Model model) {
        List<DonationEntity> donations = donationService.getAvailableDonations();
        model.addAttribute("donation", donations);
        return "/ong/vizualizarDoacoes";
    }

    @PostMapping("/accept/{id}")
    public String acceptDonation(Model model, @PathVariable Long id) {
        donationService.acceptDonation(id);
        return "redirect:/ong/vizualizar-doacoes";
    }

    @GetMapping("/minhas-doacoes")
    public String getMyDonations(Model model) {
        return "/ong/minhasDoacoes";
    }

}
