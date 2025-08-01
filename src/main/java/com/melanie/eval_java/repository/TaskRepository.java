package com.melanie.eval_java.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.melanie.eval_java.models.Task;
import com.melanie.eval_java.models.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByUser(User user);
    
    List<Task> findByUserOrderByCreatedAtDesc(User user);
    
    @Query("SELECT t FROM Task t JOIN FETCH t.user")
    List<Task> findAllWithUser();
    
    @Query("SELECT t FROM Task t JOIN FETCH t.user WHERE t.id = :id")
    Optional<Task> findByIdWithUser(Long id);
    
    List<Task> findByStatus(Task.TaskStatus status);
    
    List<Task> findByPriority(Task.TaskPriority priority);
}