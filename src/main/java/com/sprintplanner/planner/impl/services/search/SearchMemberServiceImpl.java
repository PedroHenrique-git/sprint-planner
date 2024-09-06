package com.sprintplanner.planner.impl.services.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.model.search.SearchMember;
import com.sprintplanner.planner.domain.repository.search.SearchMemberRepository;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.domain.service.search.SearchMemberService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;

@Log
@Service
public class SearchMemberServiceImpl implements SearchMemberService {
    private SearchMemberRepository searchRepository;
    private MemberService service;

    @Value("${spring.elasticsearch.force-rebuild}")
    private boolean rebuildData;

    public SearchMemberServiceImpl(SearchMemberRepository searchRepository, MemberServiceImpl service) {
        this.searchRepository = searchRepository;
        this.service = service;
    }

    @Override
    public Optional<SearchMember> get(String id) {
        return searchRepository.findById(id);
    }

    @Override
    public SearchMember create(SearchMember data) {
        return searchRepository.save(data);
    }

    @Override
    public void delete(String id) {
        searchRepository.deleteById(id);
    }

    @Override
    public List<SearchMember> getAll() {
        List<SearchMember> list = new ArrayList<>();

        var members = searchRepository.findAll();

        members.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Page<SearchMember> getAllPaged(int page, int pageSize) {
        return searchRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public SearchMember update(String id, SearchMember newMember) {
        SearchMember member = searchRepository.findById(id).orElse(null);

        if (member == null) {
            return member;
        }

        member.setId(newMember.getId());
        member.setAvatar(newMember.getAvatar());
        member.setEmail(newMember.getEmail());
        member.setFirstName(newMember.getFirstName());
        member.setLastName(newMember.getLastName());
        member.setSprints(newMember.getSprints());
        member.setTasks(newMember.getTasks());
        member.setTeams(newMember.getTeams());
        member.setUsername(newMember.getUsername());

        searchRepository.save(member);

        return member;
    }

    @PostConstruct
    public void init() {
        log.info("-------- Rebuilding member data --------");

        var allMembers = searchRepository.count();

        if (allMembers > 0 && !rebuildData) {
            return;
        }

        searchRepository.deleteAll();

        service.getAll().forEach(m -> {
            SearchMember sm = new SearchMember();

            sm.setId(m.getId());
            sm.setUsername(m.getUsername());
            sm.setAvatar(m.getAvatar());
            sm.setEmail(m.getEmail());
            sm.setFirstName(m.getFirstName());
            sm.setLastName(m.getLastName());
            sm.setSprints(m.getSprints().stream().map(Sprint::getId).toList());
            sm.setTasks(m.getTasks().stream().map(Task::getId).toList());
            sm.setTeams(m.getTeams().stream().map(Team::getId).toList());

            searchRepository.save(sm);
        });
    }
}
