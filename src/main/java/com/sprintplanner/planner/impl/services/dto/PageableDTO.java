package com.sprintplanner.planner.impl.services.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageableDTO<ModelDTO> {
    private List<ModelDTO> data;

    private PageableConfigDTO config;
}
