package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.exception.EntityNotFoundException;
import com.example.TaskManagementSystem.model.Comment;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.repository.CommentRepository;
import com.example.TaskManagementSystem.service.CommentService;
import com.example.TaskManagementSystem.service.TaskService;
import com.example.TaskManagementSystem.service.UserService;
import com.example.TaskManagementSystem.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final UserService userService;
    private final TaskService taskService;

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                MessageFormat.format("Комментарий с ID {0} не найден!", id)));
    }

    @Override
    public Comment save(Comment comment) {
        Comment commentForSaving = setAuthorAndTask(comment, comment);
        return repository.save(commentForSaving);
    }

    private Comment setAuthorAndTask(Comment source, Comment destination) {
        User author = userService.findById(source.getAuthor().getId());
        Task task = taskService.findById(source.getTask().getId());

        destination.setAuthor(author);
        destination.setTask(task);
        return destination;
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        existedComment = setAuthorAndTask(comment, existedComment);
        return repository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
