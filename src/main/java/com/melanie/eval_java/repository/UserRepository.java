package com.melanie.eval_java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.melanie.eval_java.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.tasks WHERE u.id = :id")
    Optional<User> findByIdWithTasks(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}