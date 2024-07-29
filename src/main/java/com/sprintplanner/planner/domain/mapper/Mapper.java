package com.sprintplanner.planner.domain.mapper;

import java.util.List;

public interface Mapper<Model, ModelDTO, ModelDTOResponse> {
    ModelDTO fromModelToModelDTO(Model model);
    ModelDTOResponse fromModelToModelDTOResponse(Model model);
    Model fromModelDtoToModel(ModelDTO dto);
    List<ModelDTO> fromListModelToListModelDTO(List<Model> models);
    List<ModelDTOResponse> fromListModelToListModelDTOResponse(List<Model> models);
}
