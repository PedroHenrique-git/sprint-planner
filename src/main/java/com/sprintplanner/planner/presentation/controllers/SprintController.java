package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.impl.mappers.SprintMapperImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.dto.SprintDTO;
import com.sprintplanner.planner.impl.services.dto.SprintDTOResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/sprints")
@Tag(name = "Sprint", description = "sprint API")
public class SprintController extends CrudController<Sprint, SprintDTO, SprintDTOResponse, SprintService> {
    public SprintController(SprintServiceImpl service, SprintMapperImpl mapper) {
        super(service, mapper);
    }
}
