package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.AppSecurity.CustomUserDetails;
import pl.coderslab.charity.AppSecurity.UserService;
import pl.coderslab.charity.entity.PasswordChange;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }




}
