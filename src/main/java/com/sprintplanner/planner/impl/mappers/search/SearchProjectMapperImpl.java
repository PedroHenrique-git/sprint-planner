package com.sprintplanner.planner.impl.mappers.search;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.search.SearchProject;
import com.sprintplanner.planner.domain.model.search.SearchTeam;
import com.sprintplanner.planner.domain.service.dto.search.SearchMemberDTO;
import com.sprintplanner.planner.domain.service.dto.search.SearchProjectDTO;
import com.sprintplanner.planner.domain.service.dto.search.SearchTeamDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchProjectMapperImpl implements Mapper<SearchProject, SearchProjectDTO, SearchProjectDTO> {
    private final ModelMapper modelMapper;

    public SearchProjectMapperImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public SearchProjectDTO fromModelToModelDTO(SearchProject model) {
        return modelMapper.map(model, SearchProjectDTO.class);
    }

    @Override
    public SearchProjectDTO fromModelToModelDTOResponse(SearchProject model) {
        return modelMapper.map(model, SearchProjectDTO.class);
    }

    @Override
    public SearchProject fromModelDtoToModel(SearchProjectDTO model) {
        return modelMapper.map(model, SearchProject.class);
    }

    @Override
    public List<SearchProjectDTO> fromListModelToListModelDTO(List<SearchProject> models) {
        return models.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SearchProjectDTO> fromListModelToListModelDTOResponse(List<SearchProject> models) {
        return models.stream().map(this::fromModelToModelDTOResponse).toList();
    }
}
