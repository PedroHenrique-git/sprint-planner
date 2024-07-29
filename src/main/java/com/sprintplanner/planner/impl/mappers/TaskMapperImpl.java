package com.sprintplanner.planner.impl.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TaskDTO;
import com.sprintplanner.planner.impl.services.dto.TaskDTOResponse;

import jakarta.validation.ValidationException;

@Component
public class TaskMapperImpl implements Mapper<Task, TaskDTO, TaskDTOResponse> {
    private SprintService sprintService;
    private MemberService memberService;
    private ModelMapper modelMapper;

    public TaskMapperImpl(SprintServiceImpl sprintService, MemberServiceImpl memberService) {
        this.sprintService = sprintService;
        this.memberService = memberService;

        this.modelMapper = new ModelMapper();

        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    @Override
    public List<TaskDTO> fromListModelToListModelDTO(List<Task> tasks) {
        return tasks.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<TaskDTOResponse> fromListModelToListModelDTOResponse(List<Task> tasks) {
        return tasks.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public Task fromModelDtoToModel(TaskDTO dto) {
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
    public TaskDTO fromModelToModelDTO(Task model) {
        TaskDTO dto = modelMapper.map(model, TaskDTO.class);

        dto.setMemberId(model.getMember().getId());
        dto.setSprintId(model.getSprint().getId());

        return dto;
    }

    @Override
    public TaskDTOResponse fromModelToModelDTOResponse(Task model) {
        TaskDTOResponse dto = modelMapper.map(model, TaskDTOResponse.class);

        dto.setMemberId(model.getMember().getId());
        dto.setSprintId(model.getSprint().getId());

        return dto;
    }
}
