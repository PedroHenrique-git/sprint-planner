package com.sprintplanner.planner.impl.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;
import com.sprintplanner.planner.impl.services.dto.SprintDTO;
import com.sprintplanner.planner.impl.services.dto.SprintDTOResponse;

import jakarta.validation.ValidationException;

@Component
public class SprintMapperImpl implements Mapper<Sprint, SprintDTO, SprintDTOResponse> {
    private TaskService taskService;
    private MemberService memberService;
    private ModelMapper modelMapper;

    public SprintMapperImpl(TaskServiceImpl taskService, MemberServiceImpl memberService) {
        this.taskService = taskService;
        this.memberService = memberService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SprintDTO> fromListModelToListModelDTO(List<Sprint> sprints) {
        return sprints.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SprintDTOResponse> fromListModelToListModelDTOResponse(List<Sprint> sprints) {
        return sprints.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public Sprint fromModelDtoToModel(SprintDTO dto) {
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
    public SprintDTO fromModelToModelDTO(Sprint sprint) {
        List<Member> sprintMembers = sprint.getMembers();
        List<Task> sprintTasks = sprint.getTasks();

        sprint.setMembers(List.of());
        sprint.setTasks(List.of());

        SprintDTO dto = modelMapper.map(sprint, SprintDTO.class);

        dto.setTasks(sprintTasks.stream().map(Task::getId).toList());
        dto.setMembers(sprintMembers.stream().map(Member::getId).toList());

        return dto;
    }

    @Override
    public SprintDTOResponse fromModelToModelDTOResponse(Sprint sprint) {
        List<Member> sprintMembers = sprint.getMembers();
        List<Task> sprintTasks = sprint.getTasks();

        sprint.setMembers(List.of());
        sprint.setTasks(List.of());

        SprintDTOResponse dto = modelMapper.map(sprint, SprintDTOResponse.class);

        dto.setTasks(sprintTasks.stream().map(Task::getId).toList());
        dto.setMembers(sprintMembers.stream().map(Member::getId).toList());

        return dto;
    }
}
