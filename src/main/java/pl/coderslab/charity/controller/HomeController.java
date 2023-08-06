package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    private final UserRepository userRepository;


    public HomeController(InstitutionService institutionService, DonationService donationService, UserRepository userRepository) {
        this.institutionService = institutionService;
        this.donationService = donationService;

        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionService.getAllInstitutions();
        int totalBags = donationService.sumOfAllDonatedBags();
        int totalDonations = donationService.totalDonations();
        model.addAttribute("institutions", institutions);
        model.addAttribute("totalBags", totalBags);
        model.addAttribute("totalDonations", totalDonations);
        return "home";
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







}
