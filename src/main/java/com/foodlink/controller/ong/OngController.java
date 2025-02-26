package com.foodlink.controller.ong;

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

    @GetMapping("/viewdonations")
    public String getMyDonationsCarriedOut(Model model) {
        List<DonationEntity> availableDonations = donationService.getAvailableDonations();
        model.addAttribute("donation", availableDonations);
        return "/ong/viewDonations";
    }

    @PostMapping("/accept")
    public String acceptDonation(@RequestParam("donationId") Long donationId, Model model) {
        donationService.acceptDonation(donationId);
        List<DonationEntity> availableDonations = donationService.getAvailableDonations();
        model.addAttribute("donation", availableDonations);
        return "/ong/viewDonations";
    }

    @GetMapping("/mydonations")
    public String getMyDonations(Model model) {
        List<DonationEntity> receiveDonations = donationService.getReceiveDonations();
        model.addAttribute("donation", receiveDonations);
        return "/ong/myDonations";
    }

    @GetMapping("/details/{id}")
    public String getMyDonationsCarriedOut(@PathVariable Long id, Model model) {
        DonationEntity donation = donationService.getDonationById(id);
        model.addAttribute("donation", donation);
        return "/donation/donationsDetails";
    }

}
