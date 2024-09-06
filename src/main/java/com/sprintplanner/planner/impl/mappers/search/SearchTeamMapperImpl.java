package com.sprintplanner.planner.impl.mappers.search;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.search.SearchTeam;
import com.sprintplanner.planner.domain.service.dto.search.SearchTeamDTO;

@Component
public class SearchTeamMapperImpl implements Mapper<SearchTeam, SearchTeamDTO, SearchTeamDTO> {
    private ModelMapper modelMapper;

    public SearchTeamMapperImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SearchTeamDTO> fromListModelToListModelDTO(List<SearchTeam> models) {
        return models.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SearchTeamDTO> fromListModelToListModelDTOResponse(List<SearchTeam> models) {
        return models.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public SearchTeam fromModelDtoToModel(SearchTeamDTO dto) {
        return modelMapper.map(dto, SearchTeam.class);
    }

    @Override
    public SearchTeamDTO fromModelToModelDTO(SearchTeam model) {
        return modelMapper.map(model, SearchTeamDTO.class);
    }

    @Override
    public SearchTeamDTO fromModelToModelDTOResponse(SearchTeam model) {
        return modelMapper.map(model, SearchTeamDTO.class);
    }
}
