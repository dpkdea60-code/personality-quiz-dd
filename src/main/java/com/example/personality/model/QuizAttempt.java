package com.example.personality.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @Column(nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable=false, length=4)
    private String personalityType;

    private int eiScore;
    private int snScore;
    private int tfScore;
    private int jpScore;

    public QuizAttempt() {}

    public QuizAttempt(User user, String personalityType, int eiScore, int snScore, int tfScore, int jpScore) {
        this.user = user;
        this.personalityType = personalityType;
        this.eiScore = eiScore;
        this.snScore = snScore;
        this.tfScore = tfScore;
        this.jpScore = jpScore;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getPersonalityType() { return personalityType; }
    public int getEiScore() { return eiScore; }
    public int getSnScore() { return snScore; }
    public int getTfScore() { return tfScore; }
    public int getJpScore() { return jpScore; }
}
