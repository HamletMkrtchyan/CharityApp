package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.appSecurity.UserService;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserRegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") User user, Model model) {
        if (user.getPassword().length() < 8){
            model.addAttribute("lengthError", "Hasło musi mieć przynajmniej 8 znaków");
        }
        if (!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError", "Podaj prawidłowe hasło");
            return "register";
        }
        if (userRepository.existsByEmail(user.getEmail())){
            model.addAttribute("emailError", "Email jest już używany. Proszę podać inny.");
            return "register";
        }
        userService.save(user, "ROLE_USER");
        model.addAttribute("message", "Gratulacje zostałaś rejestrowany, proszę sie zalogować");
        return "login";
    }
}
