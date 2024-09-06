package com.sprintplanner.planner.impl.mappers.search;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.model.search.SearchMember;
import com.sprintplanner.planner.domain.service.dto.search.SearchMemberDTO;

@Component
public class SearchMemberMapperImpl implements Mapper<SearchMember, SearchMemberDTO, SearchMemberDTO> {
    private ModelMapper modelMapper;

    public SearchMemberMapperImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SearchMemberDTO> fromListModelToListModelDTO(List<SearchMember> models) {
        return models.stream().map(this::fromModelToModelDTO).toList();
    }

    @Override
    public List<SearchMemberDTO> fromListModelToListModelDTOResponse(List<SearchMember> models) {
        return models.stream().map(this::fromModelToModelDTOResponse).toList();
    }

    @Override
    public SearchMember fromModelDtoToModel(SearchMemberDTO dto) {
        return modelMapper.map(dto, SearchMember.class);
    }

    @Override
    public SearchMemberDTO fromModelToModelDTO(SearchMember model) {
        return modelMapper.map(model, SearchMemberDTO.class);
    }

    @Override
    public SearchMemberDTO fromModelToModelDTOResponse(SearchMember model) {
        return modelMapper.map(model, SearchMemberDTO.class);
    }
}
