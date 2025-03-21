package com.example.TaskManagementSystem.mapper;

import com.example.TaskManagementSystem.dto.request.UpsertUserRequest;
import com.example.TaskManagementSystem.dto.response.UserListResponse;
import com.example.TaskManagementSystem.dto.response.UserResponse;
import com.example.TaskManagementSystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TaskMapper.class})
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);
    UserResponse userToResponse(User user);
    List<UserResponse> userListToResponseList(List<User> users);

    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(userListToResponseList(users));
        return userListResponse;
    }
}
