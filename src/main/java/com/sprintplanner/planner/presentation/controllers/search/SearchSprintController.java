package com.sprintplanner.planner.presentation.controllers.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.search.SearchSprint;
import com.sprintplanner.planner.domain.service.dto.search.SearchSprintDTO;
import com.sprintplanner.planner.domain.service.search.SearchSprintService;
import com.sprintplanner.planner.impl.mappers.search.SearchSprintMapperImpl;
import com.sprintplanner.planner.impl.services.search.SearchSprintServiceImpl;

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
