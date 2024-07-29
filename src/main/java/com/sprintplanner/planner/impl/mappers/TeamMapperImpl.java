package com.sprintplanner.planner.impl.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.SprintService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.SprintServiceImpl;
import com.sprintplanner.planner.impl.services.dto.TeamDTO;
import com.sprintplanner.planner.impl.services.dto.TeamDTOResponse;

import jakarta.validation.ValidationException;

@Component
public class TeamMapperImpl implements Mapper<Team, TeamDTO, TeamDTOResponse> {
    private MemberService memberService;
    private SprintService sprintService;
    private ModelMapper modelMapper;

    public TeamMapperImpl(MemberServiceImpl memberService,
            SprintServiceImpl sprintService) {

        this.memberService = memberService;
        this.sprintService = sprintService;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<TeamDTO> fromListModelToListModelDTO(List<Team> teams) {
        return teams.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<TeamDTOResponse> fromListModelToListModelDTOResponse(List<Team> teams) {
        return teams.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public Team fromModelDtoToModel(TeamDTO dto) {
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
    public TeamDTO fromModelToModelDTO(Team team) {
        List<Member> teamMembers = team.getMembers();
        List<Sprint> teamSprints = team.getSprints();

        team.setMembers(List.of());
        team.setSprints(List.of());

        TeamDTO dto = modelMapper.map(team, TeamDTO.class);

        dto.setMembers(teamMembers.stream().map(Member::getId).toList());
        dto.setSprints(teamSprints.stream().map(Sprint::getId).toList());

        return dto;
    }

    @Override
    public TeamDTOResponse fromModelToModelDTOResponse(Team team) {
        List<Member> teamMembers = team.getMembers();
        List<Sprint> teamSprints = team.getSprints();

        team.setMembers(List.of());
        team.setSprints(List.of());

        TeamDTOResponse dto = modelMapper.map(team, TeamDTOResponse.class);

        dto.setMembers(teamMembers.stream().map(Member::getId).toList());
        dto.setSprints(teamSprints.stream().map(Sprint::getId).toList());

        return dto;
    }
}
