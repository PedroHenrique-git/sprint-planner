package com.sprintplanner.planner.presentation.controllers.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.search.SearchTask;
import com.sprintplanner.planner.domain.service.dto.search.SearchTaskDTO;
import com.sprintplanner.planner.domain.service.search.SearchTaskService;
import com.sprintplanner.planner.impl.mappers.search.SearchTaskMapperImpl;
import com.sprintplanner.planner.impl.services.search.SearchTaskServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/search/tasks")
@Tag(name = "Search Task", description = "search task API")
public class SearchTaskController
        extends SearchController<SearchTask, SearchTaskDTO, SearchTaskDTO, SearchTaskService> {
    public SearchTaskController(SearchTaskServiceImpl service, SearchTaskMapperImpl mapper) {
        super(service, mapper);
    }
}
