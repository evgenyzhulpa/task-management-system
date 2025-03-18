package com.example.TaskManagementSystem.dto.response;

import com.example.TaskManagementSystem.model.TaskPriority;
import com.example.TaskManagementSystem.model.TaskStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private List<CommentResponse> comments = new ArrayList<>();
}
