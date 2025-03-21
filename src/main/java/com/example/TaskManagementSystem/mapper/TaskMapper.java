package com.example.TaskManagementSystem.mapper;

import com.example.TaskManagementSystem.dto.request.UpsertTaskRequest;
import com.example.TaskManagementSystem.dto.response.TaskListResponse;
import com.example.TaskManagementSystem.dto.response.TaskResponse;
import com.example.TaskManagementSystem.model.Task;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(TaskMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class})
public interface TaskMapper {

    Task requestToTask (UpsertTaskRequest request);
    @Mapping(source = "taskId", target = "id")
    Task requestToTask(Long taskId, UpsertTaskRequest request);
    TaskResponse taskToResponse(Task task);
    List<TaskResponse> taskListToResponseList(List<Task> tasks);

    default TaskListResponse taskListToUserListResponse(List<Task> tasks) {
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setTasks(taskListToResponseList(tasks));
        return taskListResponse;
    }
}
