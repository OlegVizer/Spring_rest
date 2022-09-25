package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/")
public class  UsersController {
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String showAddNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping("/saveUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("users/edit/{id}")
    public String showEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("users/edit/{id}")
    public String updateUserById(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

}
