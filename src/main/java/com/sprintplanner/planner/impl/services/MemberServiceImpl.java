package com.sprintplanner.planner.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprintplanner.planner.domain.enumeration.UserStatus;
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
    @Transactional
    public Optional<Member> getByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

    @Override
    @Transactional
    public List<Member> findByStatus(UserStatus status) {
        return repository.findByStatus(status);
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

        member.setStatus(newMember.getStatus());
        member.setAction(newMember.getAction());
        member.setAvatar(newMember.getAvatar());
        member.setEmail(newMember.getEmail());
        member.setUsername(newMember.getUsername());
        member.setFirstName(newMember.getFirstName());
        member.setLastName(newMember.getLastName());
        member.setPassword(newMember.getPassword());
        member.setSprints(newMember.getSprints());
        member.setTasks(newMember.getTasks());

        repository.save(member);

        return member;
    }
}
