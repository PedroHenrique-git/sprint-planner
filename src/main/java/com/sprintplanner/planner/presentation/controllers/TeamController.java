package com.sprintplanner.planner.presentation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TeamDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/v1/teams")
@Tag(name = "Team", description = "team API")
public class TeamController extends BaseController<Team, TeamDTO, TeamService> {
    private MemberService memberService;
    private SprintService sprintService;

    public TeamController(TeamServiceImpl teamService, MemberServiceImpl memberService,
            SprintServiceImpl sprintService) {
        super(teamService);

        this.memberService = memberService;
        this.sprintService = sprintService;
    }

    @Override
    protected Team mapDtoToModel(TeamDTO dto) {
        List<String> dtoMembers = dto.getMembers();
        List<String> dtoSprints = dto.getSprints();

        dto.setMembers(List.of());
        dto.setSprints(List.of());

        Team team = modelMapper.map(dto, Team.class);

        List<Member> members = new ArrayList<>();
        List<Sprint> sprints = new ArrayList<>();

        IntStream.range(0, dtoMembers.size()).forEach(position -> {
            String mId = dtoMembers.get(position);

            Member t = memberService.get(mId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, member not found", position)));

            members.add(t);
        });

        IntStream.range(0, dtoSprints.size()).forEach(position -> {
            String sId = dtoSprints.get(position);

            Sprint t = sprintService.get(sId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, sprint not found", position)));

            sprints.add(t);
        });

        team.setMembers(members);
        team.setSprints(sprints);

        return team;
    }

    @Override
    protected TeamDTO mapModelToDTO(Team team) {
        List<Member> teamMembers = team.getMembers();
        List<Sprint> teamSprints = team.getSprints();

        team.setMembers(List.of());
        team.setSprints(List.of());

        TeamDTO dto = modelMapper.map(team, TeamDTO.class);

        dto.setMembers(teamMembers.stream().map(Member::getId).toList());
        dto.setSprints(teamSprints.stream().map(Sprint::getId).toList());

        return dto;
    }
}
