package com.melanie.eval_java.controller;

// import com.melanie.eval_java.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melanie.eval_java.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        return userService.findByIdWithTasks(id)
            .map(user -> {
                model.addAttribute("user", user);
                return "users/detail";
            })
            .orElse("redirect:/users?error=notfound");
    }
}