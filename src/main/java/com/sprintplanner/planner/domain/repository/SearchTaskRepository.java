package com.sprintplanner.planner.domain.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sprintplanner.planner.domain.model.SearchTask;

@Repository
public interface SearchTaskRepository extends ElasticsearchRepository<SearchTask, String> {

}
