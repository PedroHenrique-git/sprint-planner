package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TaskDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task", description = "task API")
public class TaskController extends BaseController<Task, TaskDTO, TaskService> {
    private SprintService sprintService;
    private MemberService memberService;

    public TaskController(TaskServiceImpl service, SprintServiceImpl sprintService, MemberServiceImpl memberService) {
        super(service);

        this.sprintService = sprintService;
        this.memberService = memberService;

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    @Override
    protected Task mapDtoToModel(TaskDTO dto) {
        Task task = modelMapper.map(dto, Task.class);

        Sprint sprint = sprintService.get(dto.getSprintId())
                .orElseThrow(() -> new ValidationException("sprint not found"));

        Member member = memberService.get(dto.getMemberId())
                .orElseThrow(() -> new ValidationException("member not found"));

        task.setSprint(sprint);
        task.setMember(member);

        return task;
    }

    @Override
    protected TaskDTO mapModelToDTO(Task model) {
        TaskDTO dto = modelMapper.map(model, TaskDTO.class);

        dto.setMemberId(model.getMember().getId());
        dto.setSprintId(model.getSprint().getId());

        return dto;
    }
}
