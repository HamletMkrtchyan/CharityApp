package pl.coderslab.charity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public String profilePage(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfileForm(@RequestParam("email") String email, Principal principal, RedirectAttributes redirectAttributes) {
        User userByEmail = userRepository.findByEmail(email);

        if (userByEmail != null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Podany adres e-mail jest już w użyciu.");
            return "redirect:/profile";
        }
        User user = userRepository.findByEmail(principal.getName());

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Nie znaleziono aktualnego użytkownika.");
            return "redirect:/profile";
        }
        user.setEmail(email);
        userRepository.save(user);

        SecurityContextHolder.getContext().setAuthentication(null);

        redirectAttributes.addFlashAttribute("infoMsg", "Twój adres e-mail został zmieniony. Proszę zalogować się ponownie.");

        return "redirect:/login";
    }

    @PostMapping("/changePassword")
    public String changePasswordForm(Principal principal, Model model,
                                     RedirectAttributes redirectAttributes,
                                     @RequestParam("currentPassword") String currentPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("repeatNewPassword") String repeatNewPassword) {

        User user = userRepository.findByEmail(principal.getName());

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("infoCurrentMsg", "Podaj poprawne stare hasło");
            return "redirect:/profile";
        }

        if (newPassword.length() < 8) {
            redirectAttributes.addFlashAttribute("passwordLength", "Hasła musi zawierac 8 znaków");

        }

        if (!newPassword.equals(repeatNewPassword)) {
            redirectAttributes.addFlashAttribute("infoCurrentNewMsg", "Hasła nie są identyczne");
            return "redirect:/profile";
        }

        user.setPassword(passwordEncoder.encode(repeatNewPassword));
        redirectAttributes.addFlashAttribute("infoSuccessMsg", "Hasło został zmienione");
        userRepository.save(user);

        return "redirect:/profile";

    }


    @GetMapping("/myDonations")
    public String myDonationsPage(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName());

        List<Donation> userDonations = donationRepository.findByUserOrderByPickUpDateDesc(user);

        model.addAttribute("donationsList", userDonations);

        return "userDonationDetails";
    }

    @GetMapping("/deleteUserDonation/{id}")
    public String deleteUserDonation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));

        donationRepository.delete(donation);
        redirectAttributes.addFlashAttribute("deleteMsg", "Dar zosatał usunięty");
        return "redirect:/profile/myDonations";

    }

    @GetMapping("/editUserDonation/{id}")
    public String editUserDonation(@PathVariable Long id, Model model) {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));

        model.addAttribute("donation", donation);
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allInstitutions", institutionRepository.findAll());
        return "editUserDonation";
    }

    @PostMapping("/editUserDonation")
    public String editUserDonationForm(@ModelAttribute Donation donation) {
        Donation existingDonation = donationRepository.findById(donation.getId())
                .orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + donation.getId() + " nie została znaleziona."));

        existingDonation.setQuantity(donation.getQuantity());
        existingDonation.setCategories(donation.getCategories());
        existingDonation.setInstitution(donation.getInstitution());
        existingDonation.setStreet(donation.getStreet());
        existingDonation.setCity(donation.getCity());
        existingDonation.setZipCode(donation.getZipCode());
        existingDonation.setPickUpDate(donation.getPickUpDate());
        existingDonation.setPickUpTime(donation.getPickUpTime());
        existingDonation.setPickUpComment(donation.getPickUpComment());

        donationRepository.save(existingDonation);

        return "redirect:/profile/myDonations";
    }


}
