package com.example.TaskManagementSystem.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private List<TaskResponse> сreatedTasks = new ArrayList<>();
    private List<TaskResponse> performedTasks = new ArrayList<>();
}
