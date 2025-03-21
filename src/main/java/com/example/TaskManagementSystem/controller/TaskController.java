package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertTaskRequest;
import com.example.TaskManagementSystem.dto.response.TaskListResponse;
import com.example.TaskManagementSystem.dto.response.TaskResponse;
import com.example.TaskManagementSystem.mapper.TaskMapper;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task API v1", description = "Task API version 1")
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    @Operation(
            summary = "Get tasks",
            description = "Get all tasks",
            tags = {"task"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = TaskListResponse.class),
                    mediaType = "application/json")
    )
    @GetMapping
    public ResponseEntity<TaskListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.taskListToUserListResponse(service.findAll())
        );
    }

    @Operation(
            summary = "Get task by ID",
            description = "Get task by ID",
            tags = {"task", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable(name = "id") Long taskId) {
        return ResponseEntity.ok(
                mapper.taskToResponse(service.findById(taskId))
        );
    }

    @Operation(
            summary = "Create task",
            description = "Create new task by request data",
            tags = {"task"}
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = TaskResponse.class),
                    mediaType = "application/json")
    )
    @PostMapping
    public ResponseEntity<TaskResponse> save(@RequestBody UpsertTaskRequest request) {
        Task newTask = service.save(mapper.requestToTask(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.taskToResponse(newTask));

    }

    @Operation(
            summary = "Update task by ID",
            description = "Update task by ID and request data",
            tags = {"task", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable(name = "id") Long taskId,
                                               @RequestBody UpsertTaskRequest request) {
        Task task = service.update(mapper.requestToTask(taskId, request));
        return ResponseEntity.ok(mapper.taskToResponse(task));
    }

    @Operation(
            summary = "Delete task by ID",
            description = "Delete task by ID",
            tags = {"task", "id"}
    )
    @ApiResponse(
            responseCode = "204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long taskId) {
        service.deleteById(taskId);
        return ResponseEntity.noContent().build();
    }
}
