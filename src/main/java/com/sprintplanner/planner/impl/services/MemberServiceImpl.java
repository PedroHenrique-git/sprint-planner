package com.sprintplanner.planner.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.repository.MemberRepository;
import com.sprintplanner.planner.domain.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member create(Member member) {
        return repository.save(member);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Member> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Member> getAllPaged(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Member update(String id, Member newMember) {
        Member member = repository.findById(id).orElse(null);

        if (member == null) {
            return member;
        }

        member.setAvatar(newMember.getAvatar());
        member.setEmail(newMember.getEmail());
        member.setName(newMember.getName());
        member.setPassword(newMember.getPassword());
        member.setSprints(newMember.getSprints());
        member.setTasks(newMember.getTasks());

        repository.save(member);

        return member;
    }
}
