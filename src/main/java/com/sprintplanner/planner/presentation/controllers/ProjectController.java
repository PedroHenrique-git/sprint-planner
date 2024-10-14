package com.sprintplanner.planner.presentation.controllers;

import com.sprintplanner.planner.domain.model.Project;
import com.sprintplanner.planner.domain.service.ProjectService;
import com.sprintplanner.planner.domain.service.dto.ProjectDTO;
import com.sprintplanner.planner.domain.service.dto.ProjectDTOResponse;
import com.sprintplanner.planner.impl.mappers.ProjectMapperImpl;
import com.sprintplanner.planner.impl.services.ProjectServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
@Tag(name = "Project", description = "project API")
public class ProjectController extends CrudController<Project, ProjectDTO, ProjectDTOResponse, ProjectService> {
    public ProjectController(ProjectServiceImpl service, ProjectMapperImpl mapper) {
        super(service, mapper);
    }
}
