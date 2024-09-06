package com.sprintplanner.planner.impl.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.SearchTask;
import com.sprintplanner.planner.domain.service.dto.SearchTaskDTO;

@Component
public class SearchTaskMapperImpl implements Mapper<SearchTask, SearchTaskDTO, SearchTaskDTO> {
    private ModelMapper modelMapper;

    public SearchTaskMapperImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SearchTaskDTO> fromListModelToListModelDTO(List<SearchTask> models) {
        return models.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SearchTaskDTO> fromListModelToListModelDTOResponse(List<SearchTask> models) {
        return models.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public SearchTask fromModelDtoToModel(SearchTaskDTO dto) {
        return modelMapper.map(dto, SearchTask.class);
    }

    @Override
    public SearchTaskDTO fromModelToModelDTO(SearchTask model) {
        return modelMapper.map(model, SearchTaskDTO.class);
    }

    @Override
    public SearchTaskDTO fromModelToModelDTOResponse(SearchTask model) {
        return modelMapper.map(model, SearchTaskDTO.class);
    }
}
