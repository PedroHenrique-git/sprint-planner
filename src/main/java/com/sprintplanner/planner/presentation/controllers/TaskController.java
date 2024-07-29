package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.impl.mappers.TaskMapperImpl;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TaskDTO;
import com.sprintplanner.planner.impl.services.dto.TaskDTOResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task", description = "task API")
public class TaskController extends CrudController<Task, TaskDTO, TaskDTOResponse, TaskService> {
    public TaskController(TaskServiceImpl service, TaskMapperImpl mapper) {
        super(service, mapper);
    }
}
