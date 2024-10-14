package com.sprintplanner.planner.domain.repository.search;

import com.sprintplanner.planner.domain.model.search.SearchProject;
import com.sprintplanner.planner.domain.model.search.SearchSprint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchProjectRepository extends ElasticsearchRepository<SearchProject, String> {
}
