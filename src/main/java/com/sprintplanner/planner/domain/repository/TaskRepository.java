package com.sprintplanner.planner.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintplanner.planner.domain.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
