package com.sprintplanner.planner.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;
import com.sprintplanner.planner.impl.services.dto.MemberDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Member", description = "member API")
public class MemberController extends BaseController<Member, MemberDTO, MemberServiceImpl> {
    public MemberController(MemberServiceImpl service) {
        super(service);
    }

    @Override
    protected Member mapDtoToModel(MemberDTO dto) {
        return modelMapper.map(dto, Member.class);
    }

    @Override
    protected MemberDTO mapModelToDTO(Member model) {
        return modelMapper.map(model, MemberDTO.class);
    }
}
