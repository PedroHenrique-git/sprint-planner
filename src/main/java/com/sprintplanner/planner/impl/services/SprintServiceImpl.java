package com.sprintplanner.planner.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.repository.SprintRepository;
import com.sprintplanner.planner.domain.service.SprintService;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository repository;

    public SprintServiceImpl(SprintRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sprint create(Sprint sprint) {
        return repository.save(sprint);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Sprint> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Sprint> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Sprint> getAllPaged(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Sprint update(String id, Sprint newSprint) {
        Sprint sprint = repository.findById(id).orElse(null);

        if (sprint == null) {
            return null;
        }

        sprint.setName(newSprint.getName());
        sprint.setDescription(newSprint.getDescription());
        sprint.setTasks(newSprint.getTasks());
        sprint.setMembers(newSprint.getMembers());

        repository.save(sprint);

        return sprint;
    }

}
