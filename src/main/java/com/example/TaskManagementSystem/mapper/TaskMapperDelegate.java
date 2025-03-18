package com.example.TaskManagementSystem.mapper;

import com.example.TaskManagementSystem.dto.request.UpsertTaskRequest;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.service.UserService;
import com.example.TaskManagementSystem.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TaskMapperDelegate implements TaskMapper {

    @Autowired
    private UserService userService;

    @Override
    public Task requestToTask(UpsertTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDescription(request.getDescription());
        task.setAuthor(userService.findById(request.getAuthorId()));
        task.setPerformer(userService.findById(request.getPerformerId()));

        return task;
    }

    @Override
    public Task requestToTask(Long taskId, UpsertTaskRequest request) {
        Task task = requestToTask(request);
        task.setId(taskId);
        return task;
    }
}
