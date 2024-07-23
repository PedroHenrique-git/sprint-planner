package com.sprintplanner.planner.presentation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
import com.sprintplanner.planner.impl.services.dto.SprintDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/sprints")
@Tag(name = "Sprint", description = "sprint API")
@Log
public class SprintController extends BaseController<Sprint, SprintDTO, SprintService> {
    private TaskService taskService;
    private MemberService memberService;

    public SprintController(SprintServiceImpl service, TaskServiceImpl taskService, MemberServiceImpl memberService) {
        super(service);

        this.taskService = taskService;
        this.memberService = memberService;
    }

    @Override
    protected Sprint mapDtoToModel(SprintDTO dto) {
        List<String> dtoTasks = dto.getTasks();
        List<String> dtoMembers = dto.getMembers();

        dto.setTasks(List.of());
        dto.setMembers(List.of());

        Sprint sprint = modelMapper.map(dto, Sprint.class);

        List<Task> tasks = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        IntStream.range(0, dtoTasks.size()).forEach(position -> {
            String tId = dtoTasks.get(position);

            Task t = taskService.get(tId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, task not found", position)));

            tasks.add(t);
        });

        IntStream.range(0, dtoMembers.size()).forEach(position -> {
            String sId = dtoMembers.get(position);

            Member t = memberService.get(sId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, member not found", position)));

            members.add(t);
        });

        sprint.setTasks(tasks);
        sprint.setMembers(members);

        return sprint;
    }

    @Override
    protected SprintDTO mapModelToDTO(Sprint sprint) {
        List<Member> sprintMembers = sprint.getMembers();
        List<Task> sprintTasks = sprint.getTasks();

        sprint.setMembers(List.of());
        sprint.setTasks(List.of());

        SprintDTO dto = modelMapper.map(sprint, SprintDTO.class);

        dto.setTasks(sprintTasks.stream().map(Task::getId).toList());
        dto.setMembers(sprintMembers.stream().map(Member::getId).toList());

        return dto;
    }
}
