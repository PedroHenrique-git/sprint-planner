package com.sprintplanner.planner.impl.services.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.search.SearchTask;
import com.sprintplanner.planner.domain.repository.search.SearchTaskRepository;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.domain.service.search.SearchTaskService;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;

@Log
@Service
public class SearchTaskServiceImpl implements SearchTaskService {
    private SearchTaskRepository searchRepository;
    private TaskService service;

    @Value("${spring.elasticsearch.force-rebuild}")
    private boolean rebuildData;

    public SearchTaskServiceImpl(SearchTaskRepository searchRepository, TaskServiceImpl service) {
        this.searchRepository = searchRepository;
        this.service = service;
    }

    @Override
    public SearchTask create(SearchTask data) {
        return searchRepository.save(data);
    }

    @Override
    public void delete(String id) {
        searchRepository.deleteById(id);
    }

    @Override
    public Optional<SearchTask> get(String id) {
        return searchRepository.findById(id);
    }

    @Override
    public List<SearchTask> getAll() {
        List<SearchTask> list = new ArrayList<>();

        var tasks = searchRepository.findAll();

        tasks.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Page<SearchTask> getAllPaged(int page, int pageSize) {
        return searchRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public SearchTask update(String id, SearchTask newTask) {
        SearchTask task = searchRepository.findById(id).orElse(null);

        if (task == null) {
            return task;
        }

        task.setId(newTask.getId());
        task.setComplexity(newTask.getComplexity());
        task.setDescription(newTask.getDescription());
        task.setMemberId(newTask.getMemberId());
        task.setName(newTask.getName());
        task.setPriority(newTask.getPriority());
        task.setPunctuation(newTask.getPunctuation());
        task.setSprintId(newTask.getSprintId());

        searchRepository.save(task);

        return task;
    }

    @PostConstruct
    public void init() {
        log.info("-------- Rebuilding task data --------");

        var allTasks = searchRepository.count();

        if (allTasks > 0 && !rebuildData) {
            return;
        }

        searchRepository.deleteAll();

        service.getAll().forEach(t -> {
            SearchTask st = new SearchTask();

            st.setId(t.getId());
            st.setComplexity(t.getComplexity());
            st.setDescription(t.getDescription());
            st.setMemberId(t.getMember().getId());
            st.setName(t.getName());
            st.setPriority(t.getPriority());
            st.setPunctuation(t.getPunctuation());
            st.setSprintId(t.getSprint().getId());

            searchRepository.save(st);
        });
    }
}
