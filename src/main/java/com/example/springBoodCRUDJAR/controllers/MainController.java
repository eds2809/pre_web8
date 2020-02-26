package com.example.springBoodCRUDJAR.controllers;


import com.example.springBoodCRUDJAR.models.User;
import com.example.springBoodCRUDJAR.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getIndexPage(Model model, Authentication authentication) {
        User user = userRepository.findUserByLogin(authentication.getName());
        model.addAttribute("user",user);
        return "home";
    }
}
