package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertTaskRequest;
import com.example.TaskManagementSystem.dto.response.TaskListResponse;
import com.example.TaskManagementSystem.dto.response.TaskResponse;
import com.example.TaskManagementSystem.mapper.TaskMapper;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    @GetMapping
    public ResponseEntity<TaskListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.taskListToUserListResponse(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable(name = "id") Long taskId) {
        return ResponseEntity.ok(
                mapper.taskToResponse(service.findById(taskId))
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> save(@RequestBody UpsertTaskRequest request) {
        Task newTask = service.save(mapper.requestToTask(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.taskToResponse(newTask));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable(name = "id") Long taskId,
                                               @RequestBody UpsertTaskRequest request) {
        Task task = service.update(mapper.requestToTask(request));
        return ResponseEntity.ok(mapper.taskToResponse(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long taskId) {
        service.deleteById(taskId);
        return ResponseEntity.noContent().build();
    }
}
