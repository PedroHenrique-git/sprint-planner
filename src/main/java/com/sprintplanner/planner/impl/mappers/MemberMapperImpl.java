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
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.domain.service.dto.MemberDTO;
import com.sprintplanner.planner.domain.service.dto.MemberDTOResponse;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;

import jakarta.validation.ValidationException;

@Component
public class MemberMapperImpl implements Mapper<Member, MemberDTO, MemberDTOResponse>  {
    private TaskService taskService;
    private SprintService sprintService;
    private TeamService teamService;
    private ModelMapper modelMapper;

    public MemberMapperImpl(TaskServiceImpl taskService, SprintServiceImpl sprintService, TeamServiceImpl teamService) {
        this.taskService = taskService;
        this.sprintService = sprintService;
        this.teamService = teamService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Member fromModelDtoToModel(MemberDTO dto) {
        List<String> dtoTasks = dto.getTasks();
        List<String> dtoSprints = dto.getSprints();
        List<String> dtoTeams = dto.getTeams();

        dto.setTasks(List.of());
        dto.setSprints(List.of());
        dto.setTeams(List.of());

        Member member = modelMapper.map(dto, Member.class);

        List<Task> tasks = new ArrayList<>();
        List<Sprint> sprints = new ArrayList<>();
        List<Team> teams = new ArrayList<>();

        IntStream.range(0, dtoTasks.size()).forEach(position -> {
            String tId = dtoTasks.get(position);

            Task t = taskService.get(tId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, task not found", position)));

            tasks.add(t);
        });

        IntStream.range(0, dtoSprints.size()).forEach(position -> {
            String sId = dtoSprints.get(position);

            Sprint s = sprintService.get(sId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, sprint not found", position)));

            sprints.add(s);
        });

        IntStream.range(0, dtoTeams.size()).forEach(position -> {
            String tId = dtoTeams.get(position);

            Team t = teamService.get(tId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, team not found", position)));

            teams.add(t);
        });

        member.setTasks(tasks);
        member.setSprints(sprints);
        member.setTeams(teams);

        return member;
    }

    @Override
    public MemberDTO fromModelToModelDTO(Member member) {
        List<Task> memberTasks = member.getTasks();
        List<Sprint> memberSprints = member.getSprints();
        List<Team> memberTeams = member.getTeams();

        member.setSprints(List.of());
        member.setTasks(List.of());
        member.setTeams(List.of());

        MemberDTO dto = modelMapper.map(member, MemberDTO.class);

        dto.setTasks(memberTasks.stream().map(Task::getId).toList());
        dto.setSprints(memberSprints.stream().map(Sprint::getId).toList());
        dto.setTeams(memberTeams.stream().map(Team::getId).toList());

        return dto;
    }

    @Override
    public MemberDTOResponse fromModelToModelDTOResponse(Member member) {
        List<Task> memberTasks = member.getTasks();
        List<Sprint> memberSprints = member.getSprints();
        List<Team> memberTeams = member.getTeams();

        member.setSprints(List.of());
        member.setTasks(List.of());
        member.setTeams(List.of());

        MemberDTOResponse dto = modelMapper.map(member, MemberDTOResponse.class);

        dto.setTasks(memberTasks.stream().map(Task::getId).toList());
        dto.setSprints(memberSprints.stream().map(Sprint::getId).toList());
        dto.setTeams(memberTeams.stream().map(Team::getId).toList());

        return dto;
    }

    @Override
    public List<MemberDTO> fromListModelToListModelDTO(List<Member> members) {
        return members.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<MemberDTOResponse> fromListModelToListModelDTOResponse(List<Member> members) {
        return members.stream().map(this::fromModelToModelDTOResponse).toList();
    }
}
