package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dto.request.UpsertCommentRequest;
import com.example.TaskManagementSystem.dto.response.CommentListResponse;
import com.example.TaskManagementSystem.dto.response.CommentResponse;
import com.example.TaskManagementSystem.mapper.CommentMapper;
import com.example.TaskManagementSystem.model.Comment;
import com.example.TaskManagementSystem.service.CommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API V1", description = "Comment API version V1")
public class CommentController {

    private final CommentService service;
    private final CommentMapper mapper;

    @Operation(
            summary = "Get comments",
            description = "Get all comments",
            tags = {"comment"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CommentListResponse.class),
                    mediaType = "application/json")
    )
    @GetMapping
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.commentListToUserListResponse(service.findAll())
        );
    }

    @Operation(
            summary = "Get comment by ID",
            description = "Get comment by ID",
            tags = {"comment", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CommentResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable(name = "id") Long commentId) {
        return ResponseEntity.ok(
                mapper.commentToResponse(service.findById(commentId))
        );
    }

    @Operation(
            summary = "Create comment",
            description = "Create new comment by request data",
            tags = {"comment"}
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = CommentResponse.class),
                    mediaType = "application/json")
    )
    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody UpsertCommentRequest request) {
        Comment newComment = service.save(mapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.commentToResponse(newComment));
    }

    @Operation(
            summary = "Update comment by ID",
            description = "Update comment by ID and request data",
            tags = {"comment", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CommentResponse.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable(name = "id") Long commentId,
                                                  @RequestBody UpsertCommentRequest request) {
        Comment comment = service.update(mapper.requestToComment(commentId, request));
        return ResponseEntity.ok(mapper.commentToResponse(comment));
    }

    @Operation(
            summary = "Delete comment by ID",
            description = "Delete comment by ID",
            tags = {"comment", "id"}
    )
    @ApiResponse(
            responseCode = "204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long commentId) {
        service.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }

}
