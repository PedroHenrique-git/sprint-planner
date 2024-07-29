package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.impl.mappers.TeamMapperImpl;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TeamDTO;
import com.sprintplanner.planner.impl.services.dto.TeamDTOResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/teams")
@Tag(name = "Team", description = "team API")
public class TeamController extends CrudController<Team, TeamDTO, TeamDTOResponse, TeamService> {
    public TeamController(TeamServiceImpl teamService, TeamMapperImpl mapper) {
        super(teamService, mapper);
    }
}
