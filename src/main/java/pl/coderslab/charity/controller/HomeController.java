package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;


    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;

    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.getAllInstitutions();
        int totalBags = donationService.sumOfAllDonatedBags();
        int totalDonations = donationService.totalDonations();
        model.addAttribute("institutions", institutions);
        model.addAttribute("totalBags", totalBags);
        model.addAttribute("totalDonations", totalDonations);
        return "index";
    }

    @GetMapping("/goToLoginPage")
    public String goToLoginPage(Model model){
        return "login";
    }


    @GetMapping("/giveDonationForm")
    public String showCategory(Model model) {
        List<Category> categories = donationService.getAllCategories();
        List<Institution> institutions = donationService.getAllInstitutions();
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        return "form";
    }
}
