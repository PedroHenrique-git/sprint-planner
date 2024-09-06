package com.sprintplanner.planner.impl.mappers.search;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.search.SearchSprint;
import com.sprintplanner.planner.domain.service.dto.search.SearchSprintDTO;

@Component
public class SearchSprintMapperImpl implements Mapper<SearchSprint, SearchSprintDTO, SearchSprintDTO> {
    private ModelMapper modelMapper;

    public SearchSprintMapperImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SearchSprintDTO> fromListModelToListModelDTO(List<SearchSprint> models) {
        return models.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SearchSprintDTO> fromListModelToListModelDTOResponse(List<SearchSprint> models) {
        return models.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public SearchSprint fromModelDtoToModel(SearchSprintDTO dto) {
        return modelMapper.map(dto, SearchSprint.class);
    }

    @Override
    public SearchSprintDTO fromModelToModelDTO(SearchSprint model) {
        return modelMapper.map(model, SearchSprintDTO.class);
    }

    @Override
    public SearchSprintDTO fromModelToModelDTOResponse(SearchSprint model) {
        return modelMapper.map(model, SearchSprintDTO.class);
    }
}
