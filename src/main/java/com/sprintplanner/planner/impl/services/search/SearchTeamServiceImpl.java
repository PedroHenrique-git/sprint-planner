package com.sprintplanner.planner.impl.services.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.search.SearchTeam;
import com.sprintplanner.planner.domain.repository.search.SearchTeamRepository;
import com.sprintplanner.planner.domain.service.TeamService;
import com.sprintplanner.planner.domain.service.search.SearchTeamService;
import com.sprintplanner.planner.impl.services.TeamServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;

@Log
@Service
public class SearchTeamServiceImpl implements SearchTeamService {
    private SearchTeamRepository searchRepository;
    private TeamService service;

    @Value("${spring.elasticsearch.force-rebuild}")
    private boolean rebuildData;

    public SearchTeamServiceImpl(SearchTeamRepository searchRepository, TeamServiceImpl service) {
        this.searchRepository = searchRepository;
        this.service = service;
    }

    @Override
    public SearchTeam create(SearchTeam data) {
        return searchRepository.save(data);
    }

    @Override
    public void delete(String id) {
        searchRepository.deleteById(id);
    }

    @Override
    public Optional<SearchTeam> get(String id) {
        return searchRepository.findById(id);
    }

    @Override
    public List<SearchTeam> getAll() {
        List<SearchTeam> list = new ArrayList<>();

        var tasks = searchRepository.findAll();

        tasks.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Page<SearchTeam> getAllPaged(int page, int pageSize) {
        return searchRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public SearchTeam update(String id, SearchTeam newTeam) {
        SearchTeam team = searchRepository.findById(id).orElse(null);

        if (team == null) {
            return team;
        }

        team.setId(newTeam.getId());
        team.setDescription(newTeam.getDescription());
        team.setMembers(newTeam.getMembers());
        team.setName(newTeam.getName());
        team.setSprints(newTeam.getSprints());

        searchRepository.save(team);

        return team;
    }

    @PostConstruct
    public void init() {
        log.info("-------- Rebuilding team data --------");

        var allTasks = searchRepository.count();

        if (allTasks > 0 && !rebuildData) {
            return;
        }

        searchRepository.deleteAll();

        service.getAll().forEach(t -> {
            SearchTeam st = new SearchTeam();

            st.setId(t.getId());
            st.setDescription(t.getDescription());
            st.setMembers(t.getMembers().stream().map(Member::getId).toList());
            st.setName(t.getName());
            st.setSprints(t.getSprints().stream().map(Sprint::getId).toList());

            searchRepository.save(st);
        });
    }
}
