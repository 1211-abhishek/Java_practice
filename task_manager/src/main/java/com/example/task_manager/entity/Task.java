package com.example.task_manager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int taskId;

    private String taskTitle;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;
}
