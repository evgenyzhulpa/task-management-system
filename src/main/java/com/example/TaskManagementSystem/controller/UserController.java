package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertUserRequest;
import com.example.TaskManagementSystem.dto.response.UserListResponse;
import com.example.TaskManagementSystem.dto.response.UserResponse;
import com.example.TaskManagementSystem.mapper.UserMapper;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API V1", description = "User API version V1")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Operation(
            summary = "Get users",
            description = "Get all users",
            tags = {"user"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = UserListResponse.class),
                    mediaType = "application/json")
    )
    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.userListToUserListResponse(service.findAll())
        );
    }

    @Operation(
            summary = "Get user by ID",
            description = "Get user by ID",
            tags = {"user", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(
                mapper.userToResponse(service.findById(userId))
        );
    }

    @Operation(
            summary = "Create user",
            description = "Create new user by request data",
            tags = {"user"}
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = UserResponse.class),
                    mediaType = "application/json")
    )
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UpsertUserRequest request) {
        User newUser = service.save(mapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToResponse(newUser));
    }

    @Operation(
            summary = "Update user by ID",
            description = "Update user by ID and request data",
            tags = {"user", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable(name = "id") Long userId,
                                               @RequestBody UpsertUserRequest request) {
        User user = service.update(mapper.requestToUser(userId, request));
        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    @Operation(
            summary = "Delete user by ID",
            description = "Delete user by ID",
            tags = {"user", "id"}
    )
    @ApiResponse(
            responseCode = "204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long userId) {
        service.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
