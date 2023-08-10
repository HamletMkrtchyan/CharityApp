package pl.coderslab.charity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.AppSecurity.UserService;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/homeAdmin")
public class AdminController {
    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final UserService userService;

    public AdminController(InstitutionRepository institutionRepository, DonationService donationService, UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.donationService = donationService;
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping
    public String homeAdmin(Model model, Principal principal) {
        String email = principal.getName();
        User admin = userRepository.findByEmail(email);
        int institutions = institutionRepository.findAll().size();
        int totalBags = donationService.sumOfAllDonatedBags();
        int totalDonations = donationService.totalDonations();

        model.addAttribute("institutions", institutions);
        model.addAttribute("totalBags", totalBags);
        model.addAttribute("totalDonations", totalDonations);
        model.addAttribute("admin", admin);

        return "homeAdmin";
    }

    @GetMapping("/institutionDetails")
    public String institutionDetails(Model model, Principal principal) {
        String email = principal.getName();
        User admin = userRepository.findByEmail(email);

        List<Institution> institutions = institutionRepository.findAll();

        model.addAttribute("institutions", institutions);
        model.addAttribute("admin", admin);
        return "institutionDetails";
    }

    @GetMapping("/addInstitutionPage")
    public String addInstitutionShow(Model model) {
        Institution institution = new Institution();
        return "addInstitution";
    }

    @PostMapping("/addInstitution")
    public String addInstitutionProcess(Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/homeAdmin/institutionDetails";

    }


    @GetMapping("/deleteInstitution/{id}")
    public String deleteInstitutionForm(@PathVariable Long id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));
        ;

        institutionRepository.delete(institution);

        return "redirect:/homeAdmin/institutionDetails";

    }


    @GetMapping("/editInstitution/{id}")
    public String editInstitutionPage(@PathVariable Long id, Model model) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));
        ;
        model.addAttribute("institution", institution);
        return "editInstitution";
    }

    @PostMapping("/editInstitution/{id}")
    public String editInstitutionProcess(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("description") String description) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));

        institution.setName(name);
        institution.setDescription(description);

        institutionRepository.save(institution);

        return "redirect:/homeAdmin/institutionDetails";

    }

    @GetMapping("/adminPage")
    public String adminPageShow(Model model, Principal principal) {
        String email = principal.getName();
        User loggedUser = userRepository.findByEmail(email);
        List<User> users = userRepository.findAll();
        Role role = roleRepository.findByName("ROLE_ADMIN");

        users.sort((user1, user2) -> {
            if (user1.getId().equals(loggedUser.getId())) {
                return -1;
            } else if (user2.getId().equals(loggedUser.getId())) {
                return 1;
            } else {
                return 0;
            }
        });


        model.addAttribute("users", users);
        model.addAttribute("loggedUser", loggedUser);

        return "adminPage";
    }


    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdminForm(@PathVariable Long id) {
        User admin = userRepository.getById(id);

        userRepository.delete(admin);

        return "redirect:/homeAdmin/adminPage";

    }


    @GetMapping("/updateUserRole/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserRole(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");


        if (adminRole != null) {
            user.getRoles().add(adminRole);
            userRepository.save(user);

            redirectAttributes.addFlashAttribute("successMsg", "Użytkownikowi " + user.getEmail() + " nadano uprawnienia admina.");
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Użytkownik " + user.getEmail() + " już ma uprawnienia admina.");

        }

        return "redirect:/homeAdmin/adminPage";

    }

    @GetMapping("/deleteUserRole/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUserRole(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona."));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        if (principal.getName().equals(userRepository.findById(id).orElse(new User()).getEmail())){
            redirectAttributes.addFlashAttribute("errorMsg", "Nie możesz usunąć samego siebie!");
            return "redirect:/homeAdmin/adminPage";

        }

        if (adminRole != null) {
            user.getRoles().remove(adminRole);
            userRepository.save(user);

            redirectAttributes.addFlashAttribute("successMsg", "Użytkownikowi " + user.getEmail() + " odbierano uprawnienia admina.");
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Użytkownik " + user.getEmail() + " nie ma uprawnień admina.");
        }
        return "redirect:/homeAdmin/adminPage";
    }


    @GetMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal) {
        if (principal.getName().equals(userRepository.findById(id).orElse(new User()).getEmail())){
            redirectAttributes.addFlashAttribute("errorMsg", "Nie możesz usunąć samego siebie!");
            return "redirect:/homeAdmin/adminPage";

        }

        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instytucja o ID " + id + " nie została znaleziona.")));
        return "redirect:/homeAdmin/adminPage";

    }


}
