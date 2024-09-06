package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.SearchTask;
import com.sprintplanner.planner.domain.service.SearchTaskService;
import com.sprintplanner.planner.domain.service.dto.SearchTaskDTO;
import com.sprintplanner.planner.impl.mappers.SearchTaskMapperImpl;
import com.sprintplanner.planner.impl.services.SearchTaskServiceImpl;

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
