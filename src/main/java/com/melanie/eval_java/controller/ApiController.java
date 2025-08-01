package com.melanie.eval_java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melanie.eval_java.models.Task;
import com.melanie.eval_java.models.User;
import com.melanie.eval_java.services.TaskService;
import com.melanie.eval_java.services.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    // Users API Endpoints
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
            .map(user -> ResponseEntity.ok().body(user))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.findById(id)
            .map(user -> {
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                user.setFirstName(userDetails.getFirstName());
                user.setLastName(userDetails.getLastName());
                return ResponseEntity.ok(userService.save(user));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
            .map(user -> {
                userService.deleteById(id);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Tasks API Endpoints
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
            .map(task -> ResponseEntity.ok().body(task))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.findById(id)
            .map(task -> {
                task.setTitle(taskDetails.getTitle());
                task.setDescription(taskDetails.getDescription());
                task.setStatus(taskDetails.getStatus());
                task.setPriority(taskDetails.getPriority());
                task.setDueDate(taskDetails.getDueDate());
                return ResponseEntity.ok(taskService.save(task));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskService.findById(id)
            .map(task -> {
                taskService.deleteById(id);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Statistics endpoints
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", userService.count());
        stats.put("totalTasks", taskService.count());
        stats.put("todoTasks", taskService.countByStatus(Task.TaskStatus.TODO));
        stats.put("inProgressTasks", taskService.countByStatus(Task.TaskStatus.IN_PROGRESS));
        stats.put("doneTasks", taskService.countByStatus(Task.TaskStatus.DONE));
        return ResponseEntity.ok(stats);
    }
}