package com.melanie.eval_java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melanie.eval_java.models.Task;
import com.melanie.eval_java.models.User;
import com.melanie.eval_java.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAllWithUser();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findByIdWithUser(id);
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Task> findByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByPriority(Task.TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public long count() {
        return taskRepository.count();
    }

    public long countByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status).size();
    }

    public long countByUser(User user) {
        return taskRepository.findByUser(user).size();
    }
}