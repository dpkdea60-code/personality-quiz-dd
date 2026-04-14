package com.example.personality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.personality.model.QuizAttempt;
import com.example.personality.model.User;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUserOrderByCreatedAtDesc(User user);
}