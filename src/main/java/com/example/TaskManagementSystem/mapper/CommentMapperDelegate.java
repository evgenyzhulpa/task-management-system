package com.example.TaskManagementSystem.mapper;

import com.example.TaskManagementSystem.dto.request.UpsertCommentRequest;
import com.example.TaskManagementSystem.model.Comment;
import com.example.TaskManagementSystem.service.TaskService;
import com.example.TaskManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setDescription(request.getDescription());
        comment.setTask(taskService.findById(request.getTaskId()));
        comment.setAuthor(userService.findById(request.getAuthorId()));

        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }
}
