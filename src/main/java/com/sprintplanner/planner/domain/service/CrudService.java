package com.sprintplanner.planner.domain.service;

import java.util.List;

public interface CrudService<Model> {
    Model create(Model data);

    Model update(String id, Model data);

    void delete(String id);

    Model get(String id);

    List<Model> getAll();
}
