package com.sprintplanner.planner.presentation.controllers.search;

import com.sprintplanner.planner.domain.model.search.SearchProject;
import com.sprintplanner.planner.domain.service.dto.search.SearchProjectDTO;
import com.sprintplanner.planner.domain.service.search.SearchProjectService;
import com.sprintplanner.planner.impl.mappers.search.SearchMemberMapperImpl;
import com.sprintplanner.planner.impl.mappers.search.SearchProjectMapperImpl;
import com.sprintplanner.planner.impl.services.search.SearchMemberServiceImpl;
import com.sprintplanner.planner.impl.services.search.SearchProjectServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search/projects")
@Tag(name = "Search project", description = "search project API")
public class SearchProjectController extends SearchController<SearchProject, SearchProjectDTO, SearchProjectDTO, SearchProjectService> {
    public SearchProjectController(SearchProjectServiceImpl service, SearchProjectMapperImpl mapper) {
        super(service, mapper);
    }
}
