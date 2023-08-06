package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.AppSecurity.UserService;
import pl.coderslab.charity.dto.UserRegistrationDto;
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
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, @RequestParam("password2") String password2, Model model) {
        if (!userRegistrationDto.getPassword().equals(password2)){
            model.addAttribute("passwordError", "Podaj prawidłowe hasło");
            return "register";
        }
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())){
            model.addAttribute("emailError", "Email jest już używany. Proszę podać inny.");
            return "register";
        }
        userService.save(userRegistrationDto);
        model.addAttribute("message", "Gratulacje zostałaś rejestrowany, proszę sie zalogować");
        return "login";
    }
}
