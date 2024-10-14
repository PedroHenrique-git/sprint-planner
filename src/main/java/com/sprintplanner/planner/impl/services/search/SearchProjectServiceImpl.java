package com.sprintplanner.planner.impl.services.search;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.model.search.SearchMember;
import com.sprintplanner.planner.domain.model.search.SearchProject;
import com.sprintplanner.planner.domain.repository.search.SearchProjectRepository;
import com.sprintplanner.planner.domain.service.ProjectService;
import com.sprintplanner.planner.domain.service.search.SearchProjectService;
import com.sprintplanner.planner.impl.mappers.search.SearchProjectMapperImpl;
import com.sprintplanner.planner.impl.services.ProjectServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class SearchProjectServiceImpl implements SearchProjectService {
    private final SearchProjectRepository searchRepository;
    private final ProjectService service;

    @Value("${spring.elasticsearch.force-rebuild}")
    private boolean rebuildData;

    public SearchProjectServiceImpl(SearchProjectRepository searchRepository, ProjectServiceImpl service) {
        this.searchRepository = searchRepository;
        this.service = service;
    }

    @Override
    public SearchProject create(SearchProject project) {
        return searchRepository.save(project);
    }

    @Override
    public SearchProject update(String id, SearchProject newProject) {
        SearchProject project = searchRepository.findById(id).orElse(null);

        if (project == null) {
            return project;
        }

        project.setId(newProject.getId());
        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());
        project.setTeams(newProject.getTeams());

        searchRepository.save(project);

        return project;
    }

    @Override
    public void delete(String id) {
        searchRepository.deleteById(id);
    }

    @Override
    public Optional<SearchProject> get(String id) {
        return searchRepository.findById(id);
    }

    @Override
    public List<SearchProject> getAll() {
        List<SearchProject> list = new ArrayList<>();

        var projects = searchRepository.findAll();

        projects.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Page<SearchProject> getAllPaged(int page, int pageSize) {
        return searchRepository.findAll(PageRequest.of(page, pageSize));
    }

    @PostConstruct
    private void init() {
        log.info("-------- Rebuilding project data --------");

        var allProjects = searchRepository.count();

        if (allProjects > 0 && !rebuildData) {
            return;
        }

        searchRepository.deleteAll();

        service.getAll().forEach(p -> {
            SearchProject sp = new SearchProject();

            sp.setId(p.getId());
            sp.setDescription(p.getDescription());
            sp.setName(p.getName());
            sp.setTeams(p.getTeams().stream().map(Team::getId).toList());

            searchRepository.save(sp);
        });
    }
}
