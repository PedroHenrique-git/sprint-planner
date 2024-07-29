package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.impl.mappers.MemberMapperImpl;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.dto.MemberDTO;
import com.sprintplanner.planner.impl.services.dto.MemberDTOResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Member", description = "member API")
public class MemberController extends CrudController<Member, MemberDTO, MemberDTOResponse, MemberService> {
    public MemberController(MemberServiceImpl service, MemberMapperImpl mapper) {
        super(service, mapper);
    }
}
