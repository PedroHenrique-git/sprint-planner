
package com.sprintplanner.planner.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.repository.TeamRepository;
import com.sprintplanner.planner.domain.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository repository;

    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public Team create(Team team) {
        return repository.save(team);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Team> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Team> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Team> getAllPaged(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Team update(String id, Team newTeam) {
        Team team = repository.findById(id).orElse(null);

        if (team == null) {
            return team;
        }

        team.setName(newTeam.getName());
        team.setDescription(newTeam.getDescription());
        team.setMembers(newTeam.getMembers());
        team.setSprints(newTeam.getSprints());

        repository.save(team);

        return team;
    }
}
