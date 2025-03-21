package com.example.TaskManagementSystem.dto.request;

import com.example.TaskManagementSystem.model.TaskPriority;
import com.example.TaskManagementSystem.model.TaskStatus;
import lombok.Data;

@Data
public class UpsertTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long performerId;
    private Long authorId;
}
