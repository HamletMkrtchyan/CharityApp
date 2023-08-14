package pl.coderslab.charity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.appSecurity.UserService;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final DonationRepository donationRepository;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.donationRepository = donationRepository;
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
    public String myDonationsPage(Principal principal, Model model){
        User user = userRepository.findByEmail(principal.getName());

        List<Donation> userDonations = donationRepository.findByUser(user);

        model.addAttribute("donationsList", userDonations);

        return "userDonationDetails";
    }




}
