package com.sprintplanner.planner.domain.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sprintplanner.planner.domain.model.search.SearchSprint;

@Repository
public interface SearchSprintRepository extends ElasticsearchRepository<SearchSprint, String> {

}
