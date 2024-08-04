package com.sprintplanner.planner.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageableConfigDTO {
    private int pageSize;

    private int page;

    private int totalPages;

    private long totalElements;
}
