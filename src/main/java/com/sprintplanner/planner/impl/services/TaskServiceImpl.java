package com.sprintplanner.planner.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.repository.TaskRepository;
import com.sprintplanner.planner.domain.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task create(Task task) {
        return repository.save(task);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Task> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Override
    public Task update(String id, Task newTask) {
        Task task = repository.findById(id).orElse(null);

        if (task == null) {
            return task;
        }

        task.setComplexity(newTask.getComplexity());
        task.setDescription(newTask.getDescription());
        task.setMember(newTask.getMember());
        task.setName(newTask.getName());
        task.setPriority(newTask.getPriority());
        task.setPunctuation(newTask.getPunctuation());
        task.setSprint(newTask.getSprint());

        repository.save(task);

        return task;
    }
}
