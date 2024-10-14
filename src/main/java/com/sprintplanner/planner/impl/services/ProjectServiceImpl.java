package com.sprintplanner.planner.impl.services;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Project;
import com.sprintplanner.planner.domain.repository.ProjectRepository;
import com.sprintplanner.planner.domain.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project create(Project project) {
        return repository.save(project);
    }

    @Override
    public Project update(String id, Project newProject) {
        Project project = repository.findById(id).orElse(null);

        if (project == null) {
            return project;
        }

        project.setId(newProject.getId());
        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());
        project.setTeams(newProject.getTeams());

        repository.save(project);

        return project;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Project> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Project> getAllPaged(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }
}
