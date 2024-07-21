package com.sprintplanner.planner.presentation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import jakarta.validation.ValidationException;

public abstract class BaseController<Model, ModelDTO, ModelService extends CrudService<Model>> {
    private final ModelService service;
    protected final ModelMapper modelMapper;

    protected BaseController(ModelService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    @Operation(summary = "Get all elements")
    public ResponseEntity<List<ModelDTO>> getAll() {
        List<Model> data = service.getAll();

        return ResponseEntity.ok().body(mapListModelToListDTO(data));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<ModelDTO> get(@PathVariable String id) {
        Optional<Model> data = service.get(id);

        if (!data.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapModelToDTO(data.get()));
    }

    @PostMapping
    @Operation(summary = "Create a new element")
    public ResponseEntity<ModelDTO> create(@RequestBody @Valid ModelDTO payload) {
        Model data = service.create(mapDtoToModel(payload));

        return ResponseEntity.ok().body(mapModelToDTO(data));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update element")
    public ResponseEntity<ModelDTO> update(@PathVariable String id, @RequestBody @Valid ModelDTO payload) {
        Model data = service.update(id, mapDtoToModel(payload));

        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapModelToDTO(data));
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

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handler(ValidationException exception) {
        Map<String, String> body = new HashMap<>();

        body.put("message", exception.getMessage());

        return ResponseEntity.badRequest().body(body);
    }

    protected List<ModelDTO> mapListModelToListDTO(List<Model> listModel) {
        return listModel.stream().map(this::mapModelToDTO).toList();
    }

    protected abstract Model mapDtoToModel(ModelDTO dto);

    protected abstract ModelDTO mapModelToDTO(Model model);
}
