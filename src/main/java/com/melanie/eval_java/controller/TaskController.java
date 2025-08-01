package com.melanie.eval_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melanie.eval_java.models.Task;
import com.melanie.eval_java.services.TaskService;
import com.melanie.eval_java.services.UserService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String taskDetail(@PathVariable Long id, Model model) {
        return taskService.findById(id)
            .map(task -> {
                model.addAttribute("task", task);
                return "tasks/detail";
            })
            .orElse("redirect:/tasks?error=notfound");
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("statuses", Task.TaskStatus.values());
        model.addAttribute("priorities", Task.TaskPriority.values());
        return "tasks/form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        return taskService.findById(id)
            .map(task -> {
                model.addAttribute("task", task);
                model.addAttribute("users", userService.findAll());
                model.addAttribute("statuses", Task.TaskStatus.values());
                model.addAttribute("priorities", Task.TaskPriority.values());
                return "tasks/form";
            })
            .orElse("redirect:/tasks?error=notfound");
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}