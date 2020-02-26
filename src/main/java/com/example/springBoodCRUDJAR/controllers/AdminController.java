package com.example.springBoodCRUDJAR.controllers;


import com.example.springBoodCRUDJAR.models.User;
import com.example.springBoodCRUDJAR.repositories.RoleRepository;
import com.example.springBoodCRUDJAR.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class AdminController {
    private final RoleRepository roleRepository;
    private final UserService userService;

    public AdminController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getUserPage(Model model) {

        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "adminHome";
    }

    @PostMapping(value = "/admin/add-new-user")
    public String addNewUser(@RequestParam(value = "login") String login,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "pass") String pass,
                             @RequestParam(value = "role") String role) {


        userService.save(
                login,
                pass,
                email,
                role
        );
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteByID(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/update/{id}")
    public String updateUser(@PathVariable Long id, Model model) {

       User user = userService.getByID(id);

        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("selected", user.getRoles().toArray()[0]);
        return "updateUser";
    }

    @PostMapping(value = "/admin/update")
    public String updateUser(@RequestParam(value = "id") Long id,
                             @RequestParam(value = "login") String login,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "pass") String pass,
                             @RequestParam(value = "role") String role) {

        userService.updateUser(
                id,
                login,
                pass,
                email,
                role
        );
        return "redirect:/admin";
    }

}
