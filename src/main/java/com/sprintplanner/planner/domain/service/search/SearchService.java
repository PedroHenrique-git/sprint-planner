package com.sprintplanner.planner.domain.service.search;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface SearchService<SearchModel> {
    SearchModel create(SearchModel data);

    SearchModel update(String id, SearchModel data);

    void delete(String id);

    Optional<SearchModel> get(String id);

    List<SearchModel> getAll();

    Page<SearchModel> getAllPaged(int page, int pageSize);
}
