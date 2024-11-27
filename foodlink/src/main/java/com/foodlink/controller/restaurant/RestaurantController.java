package com.foodlink.controller.restaurant;

import com.foodlink.entity.DonationEntity;
import com.foodlink.entity.UserEntity;
import com.foodlink.service.DonationService;
import com.foodlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @GetMapping("/donation")
    public String getRestaurants(Model model) {
        model.addAttribute("donation", new DonationEntity());
        return "/restaurant/createDonations";
    }

    @PostMapping("/submit-donation")
    public String saveDonation(@ModelAttribute("donation") DonationEntity donation, Principal principal, Model model) {
        try {
            String cnpj = principal.getName();
            System.out.println("CNPJ do usuário logado: " + cnpj);
            UserEntity user = userService.findByCnpj(cnpj);

            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
            donation.setUser(user);
            donationService.saveDonation(donation, principal);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/restaurant/createDonations";
        }
    }

    @GetMapping("/mydonations")
    public String getMyDonationsCarriedOut(Model model) {
        List<DonationEntity> receiveDonations = donationService.getReceiveDonations();
        model.addAttribute("donation", receiveDonations);
        return "/restaurant/donationsCarriedOut";
    }

    @GetMapping("/details/{id}")
    public String getMyDonationsCarriedOut(@PathVariable Long id, Model model) {
        DonationEntity donation = donationService.getDonationById(id);
        model.addAttribute("donation", donation);
        return "/donation/donationsDetails";
    }
}
