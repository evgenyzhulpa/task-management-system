package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertUserRequest;
import com.example.TaskManagementSystem.dto.response.UserListResponse;
import com.example.TaskManagementSystem.dto.response.UserResponse;
import com.example.TaskManagementSystem.mapper.UserMapper;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.userListToUserListResponse(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(
                mapper.userToResponse(service.findById(userId))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UpsertUserRequest request) {
        User newUser = service.save(mapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable(name = "id") Long userId,
                                               @RequestBody UpsertUserRequest request) {
        User user = service.update(mapper.requestToUser(userId, request));
        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long userId) {
        service.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
