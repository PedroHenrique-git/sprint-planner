package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.SearchTeam;
import com.sprintplanner.planner.domain.service.SearchTeamService;
import com.sprintplanner.planner.domain.service.dto.SearchTeamDTO;
import com.sprintplanner.planner.impl.mappers.SearchTeamMapperImpl;
import com.sprintplanner.planner.impl.services.SearchTeamServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/search/teams")
@Tag(name = "Search team", description = "search team API")
public class SearchTeamController
        extends SearchController<SearchTeam, SearchTeamDTO, SearchTeamDTO, SearchTeamService> {
    public SearchTeamController(SearchTeamServiceImpl service, SearchTeamMapperImpl mapper) {
        super(service, mapper);
    }
}
