package com.example.TaskManagementSystem.mapper;

import com.example.TaskManagementSystem.dto.request.UpsertCommentRequest;
import com.example.TaskManagementSystem.dto.response.CommentListResponse;
import com.example.TaskManagementSystem.dto.response.CommentResponse;
import com.example.TaskManagementSystem.model.Comment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment requestToComment (UpsertCommentRequest request);
    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);
    CommentResponse commentToResponse(Comment comment);
    List<CommentResponse> commentListToResponseList(List<Comment> comments);

    default CommentListResponse commentListToUserListResponse(List<Comment> comments) {
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComments(commentListToResponseList(comments));
        return commentListResponse;
    }
}
