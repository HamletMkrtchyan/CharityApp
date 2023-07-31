package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.AppSecurity.UserService;
import pl.coderslab.charity.dto.UserRegistrationDto;

@Controller
public class UserRegistrationController {
    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerForm")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/registerForm")
    public String registerForm(@ModelAttribute("user") UserRegistrationDto userRegistrationDto, @RequestParam("password2") String password2, Model model) {
        if (!userRegistrationDto.getPassword().equals(password2)){
            return "register";
        }
        userService.save(userRegistrationDto);
        return "redirect:/loginForm";
    }
}
