package com.melanie.eval_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.melanie.eval_java.services.TaskService;
import com.melanie.eval_java.services.UserService;

@Controller
public class MainController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping("/")
    public String index(Model model) {
        
        model.addAttribute("totalUsers", userService.count());
        model.addAttribute("totalTasks", taskService.count());
        return "index";
    }
}