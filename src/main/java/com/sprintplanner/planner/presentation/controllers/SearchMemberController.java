package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.SearchMember;
import com.sprintplanner.planner.domain.service.SearchMemberService;
import com.sprintplanner.planner.domain.service.dto.SearchMemberDTO;
import com.sprintplanner.planner.impl.mappers.SearchMemberMapperImpl;
import com.sprintplanner.planner.impl.services.SearchMemberServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/search/members")
@Tag(name = "Search Member", description = "search member API")
public class SearchMemberController
        extends SearchController<SearchMember, SearchMemberDTO, SearchMemberDTO, SearchMemberService> {
    public SearchMemberController(SearchMemberServiceImpl service, SearchMemberMapperImpl mapper) {
        super(service, mapper);
    }
}
