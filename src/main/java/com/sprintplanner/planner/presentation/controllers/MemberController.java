package com.sprintplanner.planner.presentation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.domain.service.TaskService;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.TaskServiceImpl;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;
import com.sprintplanner.planner.impl.services.dto.MemberDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Member", description = "member API")
public class MemberController extends BaseController<Member, MemberDTO, MemberService> {
    private TaskService taskService;
    private SprintService sprintService;
    private TeamService teamService;

    public MemberController(MemberServiceImpl service, TaskServiceImpl taskService, SprintServiceImpl sprintService, TeamServiceImpl teamService) {
        super(service);

        this.taskService = taskService;
        this.sprintService = sprintService;
        this.teamService = teamService;
    }

    @Override
    protected Member mapDtoToModel(MemberDTO dto) {
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
    protected MemberDTO mapModelToDTO(Member member) {
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
}
