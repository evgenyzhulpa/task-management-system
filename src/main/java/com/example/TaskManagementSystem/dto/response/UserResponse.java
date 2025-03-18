package com.example.TaskManagementSystem.dto.response;

import com.example.TaskManagementSystem.model.Task;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private List<TaskResponse> сreatedTasks = new ArrayList<>();
    private List<TaskResponse> performedTasks = new ArrayList<>();
}
