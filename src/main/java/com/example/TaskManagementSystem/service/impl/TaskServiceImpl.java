package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.exception.EntityNotFoundException;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.repository.TaskRepository;
import com.example.TaskManagementSystem.service.TaskService;
import com.example.TaskManagementSystem.service.UserService;
import com.example.TaskManagementSystem.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserService clientService;
    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Задача с ID {0} не найдена!", id)));
    }

    @Override
    public Task save(Task task) {
        Task taskForSaving = setAuthorAndPerformer(task, task);
        return repository.save(taskForSaving);
    }

    private Task setAuthorAndPerformer(Task source, Task destination) {
        User author = clientService.findById(destination.getAuthor().getId());
        User performer = clientService.findById(destination.getPerformer().getId());

        source.setAuthor(author);
        source.setPerformer(performer);
        return destination;
    }

    @Override
    public Task update(Task task) {
        Task existedTask = findById(task.getId());
        BeanUtils.copyNonNullProperties(task, existedTask);
        existedTask = setAuthorAndPerformer(task, existedTask);
        return repository.save(existedTask);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
