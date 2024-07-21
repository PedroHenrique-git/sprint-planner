package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.dto.SprintDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/sprints")
@Tag(name = "Sprint", description = "sprint API")
public class SprintController extends BaseController<Sprint, SprintDTO, SprintService> {
    public SprintController(SprintServiceImpl service) {
        super(service);
    }

    @Override
    protected Sprint mapDtoToModel(SprintDTO dto) {
        return modelMapper.map(dto, Sprint.class);
    }

    @Override
    protected SprintDTO mapModelToDTO(Sprint model) {
        return modelMapper.map(model, SprintDTO.class);
    }
}
