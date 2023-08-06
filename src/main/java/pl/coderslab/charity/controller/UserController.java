package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.AppSecurity.UserService;
import pl.coderslab.charity.entity.PasswordChange;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);

        PasswordChange passwordChange = new PasswordChange();

        model.addAttribute("passwordChange", passwordChange);
        model.addAttribute("user", user);

        return "profile";
    }

@PostMapping("/updateEmail")
    public String updateEmail(@ModelAttribute User user){

        return "redirect:/profile";
}












}
