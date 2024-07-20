package com.sprintplanner.planner.presentation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sprintplanner.planner.domain.service.CrudService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

public abstract class BaseController<Model, ModelDTO, ModelService extends CrudService<Model>> {
    private final ModelService service;
    protected final ModelMapper modelMapper;

    protected BaseController(ModelService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    @Operation(summary = "Get all elements")
    public ResponseEntity<List<Model>> getAll() {
        List<Model> data = service.getAll();

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<Model> get(@PathVariable String id) {
        Model data = service.get(id);

        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(data);
    }

    @PostMapping
    @Operation(summary = "Create a new element")
    public ResponseEntity<Model> create(@RequestBody @Valid ModelDTO payload) {
        Model data = service.create(mapDtoToModel(payload));

        return ResponseEntity.ok().body(data);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update element")
    public ResponseEntity<Model> update(@PathVariable String id, @RequestBody @Valid ModelDTO payload) {
        Model data = service.update(id, mapDtoToModel(payload));

        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete element")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException exception) {
        Map<String, String> body = new HashMap<>();

        exception.getFieldErrors().forEach(e -> body.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(body);
    }

    protected abstract Model mapDtoToModel(ModelDTO dto);

    protected abstract ModelDTO mapModelToDTO(Model model);
}
