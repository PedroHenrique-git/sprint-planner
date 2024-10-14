package com.sprintplanner.planner.domain.repository;

import com.sprintplanner.planner.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
