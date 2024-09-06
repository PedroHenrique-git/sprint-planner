package com.sprintplanner.planner.impl.services.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.search.SearchSprint;
import com.sprintplanner.planner.domain.repository.search.SearchSprintRepository;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.domain.service.search.SearchSprintService;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;

@Log
@Service
public class SearchSprintServiceImpl implements SearchSprintService {
    private SearchSprintRepository searchRepository;
    private SprintService service;

    @Value("${spring.elasticsearch.force-rebuild}")
    private boolean rebuildData;

    public SearchSprintServiceImpl(SearchSprintRepository searchRepository, SprintServiceImpl service) {
        this.searchRepository = searchRepository;
        this.service = service;
    }

    @Override
    public SearchSprint create(SearchSprint data) {
        return searchRepository.save(data);
    }

    @Override
    public void delete(String id) {
        searchRepository.deleteById(id);
    }

    @Override
    public Optional<SearchSprint> get(String id) {
        return searchRepository.findById(id);
    }

    @Override
    public List<SearchSprint> getAll() {
        List<SearchSprint> list = new ArrayList<>();

        var tasks = searchRepository.findAll();

        tasks.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Page<SearchSprint> getAllPaged(int page, int pageSize) {
        return searchRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public SearchSprint update(String id, SearchSprint newSprint) {
        SearchSprint sprint = searchRepository.findById(id).orElse(null);

        if (sprint == null) {
            return sprint;
        }

        sprint.setId(newSprint.getId());
        sprint.setTeamId(newSprint.getTeamId());
        sprint.setDescription(newSprint.getDescription());
        sprint.setMembers(newSprint.getMembers());
        sprint.setName(newSprint.getName());
        sprint.setTasks(newSprint.getTasks());

        searchRepository.save(sprint);

        return sprint;
    }

    @PostConstruct
    public void init() {
        log.info("-------- Rebuilding sprint data --------");

        var allTasks = searchRepository.count();

        if (allTasks > 0 && !rebuildData) {
            return;
        }

        searchRepository.deleteAll();

        service.getAll().forEach(s -> {
            SearchSprint ss = new SearchSprint();

            ss.setId(s.getId());
            ss.setDescription(s.getDescription());
            ss.setMembers(s.getMembers().stream().map(Member::getId).toList());
            ss.setName(s.getName());
            ss.setTeamId(s.getTeam().getId());
            ss.setTasks(s.getTasks().stream().map(Task::getId).toList());

            searchRepository.save(ss);
        });
    }
}
