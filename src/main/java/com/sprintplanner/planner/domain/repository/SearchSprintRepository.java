package com.sprintplanner.planner.domain.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sprintplanner.planner.domain.model.SearchSprint;

@Repository
public interface SearchSprintRepository extends ElasticsearchRepository<SearchSprint, String> {

}
