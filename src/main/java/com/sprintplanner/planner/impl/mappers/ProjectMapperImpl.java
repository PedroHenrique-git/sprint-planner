package com.sprintplanner.planner.impl.mappers;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Project;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.domain.service.dto.ProjectDTO;
import com.sprintplanner.planner.domain.service.dto.ProjectDTOResponse;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class ProjectMapperImpl implements Mapper<Project, ProjectDTO, ProjectDTOResponse> {
    private final TeamService teamService;
    private final ModelMapper modelMapper;

    public ProjectMapperImpl(TeamServiceImpl teamService) {
        this.teamService = teamService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ProjectDTO fromModelToModelDTO(Project project) {
        List<Team> teams = project.getTeams();

        project.setTeams(List.of());

        ProjectDTO dto = modelMapper.map(project, ProjectDTO.class);

        dto.setTeams(teams.stream().map(Team::getId).toList());

        return dto;
    }

    @Override
    public ProjectDTOResponse fromModelToModelDTOResponse(Project project) {
        List<Team> teams = project.getTeams();

        project.setTeams(List.of());

        ProjectDTOResponse dto = modelMapper.map(project, ProjectDTOResponse.class);

        dto.setTeams(teams.stream().map(Team::getId).toList());

        return dto;
    }

    @Override
    public Project fromModelDtoToModel(ProjectDTO projectDTO) {
        List<String> dtoTeams = projectDTO.getTeams();

        projectDTO.setTeams(List.of());

        Project project = modelMapper.map(projectDTO, Project.class);

        List<Team> teams = new ArrayList<>();

        IntStream.range(0, dtoTeams.size()).forEach(position -> {
            String tId = dtoTeams.get(position);

            Team t = teamService.get(tId)
                    .orElseThrow(
                            () -> new ValidationException(String.format("position: %d, team not found", position)));

            teams.add(t);
        });

        project.setTeams(teams);

        return project;
    }

    @Override
    public List<ProjectDTO> fromListModelToListModelDTO(List<Project> projects) {
        return projects.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<ProjectDTOResponse> fromListModelToListModelDTOResponse(List<Project> projects) {
        return projects.stream().map(this::fromModelToModelDTOResponse).toList();
    }
}
