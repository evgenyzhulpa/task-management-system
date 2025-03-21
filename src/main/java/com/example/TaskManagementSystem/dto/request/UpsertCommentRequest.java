package com.example.TaskManagementSystem.dto.request;

import lombok.Data;

@Data
public class UpsertCommentRequest {
    private String description;
    private Long taskId;
    private Long authorId;
}
