package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertCommentRequest;
import com.example.TaskManagementSystem.dto.response.CommentListResponse;
import com.example.TaskManagementSystem.dto.response.CommentResponse;
import com.example.TaskManagementSystem.mapper.CommentMapper;
import com.example.TaskManagementSystem.model.Comment;
import com.example.TaskManagementSystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;
    private final CommentMapper mapper;

    @GetMapping
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.commentListToUserListResponse(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable(name = "id") Long commentId) {
        return ResponseEntity.ok(
                mapper.commentToResponse(service.findById(commentId))
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody UpsertCommentRequest request) {
        Comment newComment = service.save(mapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.commentToResponse(newComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable(name = "id") Long commentId,
                                                  @RequestBody UpsertCommentRequest request) {
        Comment comment = service.update(mapper.requestToComment(request));
        return ResponseEntity.ok(mapper.commentToResponse(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long commentId) {
        service.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }

}
