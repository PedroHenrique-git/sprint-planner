package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.SearchSprint;
import com.sprintplanner.planner.domain.service.SearchSprintService;
import com.sprintplanner.planner.domain.service.dto.SearchSprintDTO;
import com.sprintplanner.planner.impl.mappers.SearchSprintMapperImpl;
import com.sprintplanner.planner.impl.services.SearchSprintServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/search/sprints")
@Tag(name = "Search Sprint", description = "search sprint API")
public class SearchSprintController
        extends SearchController<SearchSprint, SearchSprintDTO, SearchSprintDTO, SearchSprintService> {
    public SearchSprintController(SearchSprintServiceImpl service, SearchSprintMapperImpl mapper) {
        super(service, mapper);
    }
}
