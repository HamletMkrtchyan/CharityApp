package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;

import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {
    private final DonationService donationService;
    private final UserRepository userRepository;


    public DonationController(DonationService donationService, UserRepository userRepository) {
        this.donationService = donationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/giveDonationForm")
    public String showCategory(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        List<Category> categories = donationService.getAllCategories();
        List<Institution> institutions = donationService.getAllInstitutions();
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("user", user);
        return "form";
    }


    @PostMapping("/form-confirmation")
    public String formConfirmationPage(@ModelAttribute Donation donation) {
        donationService.saveDonation(donation);
        return "form-confirmation";
    }



}
