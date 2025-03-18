package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    Task update(Task task);
    void deleteById(Long id);
}
