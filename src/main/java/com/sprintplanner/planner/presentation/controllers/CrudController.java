package com.sprintplanner.planner.presentation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.service.CrudService;
import com.sprintplanner.planner.impl.services.dto.PageableConfigDTO;
import com.sprintplanner.planner.impl.services.dto.PageableDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

public abstract class CrudController<Model, ModelDTO, ModelDTOResponse, ModelService extends CrudService<Model>> {
    private final ModelService service;
    protected final Mapper<Model, ModelDTO, ModelDTOResponse> mapper;

    protected CrudController(ModelService service, Mapper<Model, ModelDTO, ModelDTOResponse> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all elements")
    public ResponseEntity<List<ModelDTOResponse>> getAll() {
        List<Model> data = service.getAll();

        return ResponseEntity.ok().body(mapper.fromListModelToListModelDTOResponse(data));
    }

    @GetMapping("/paged")
    @Operation(summary = "Get all paged elements")
    public ResponseEntity<PageableDTO<ModelDTOResponse>> getAllPaged(
            @RequestParam(required = true, value = "page") int page,
            @RequestParam(required = true, value = "pageSize") int pageSize) {
        PageableDTO<ModelDTOResponse> response = new PageableDTO<>();
        PageableConfigDTO config = new PageableConfigDTO();

        Page<Model> pageableContent = service.getAllPaged(page, pageSize);
        List<ModelDTOResponse> data = mapper.fromListModelToListModelDTOResponse(pageableContent.getContent());

        config.setPage(pageableContent.getPageable().getPageNumber());
        config.setPageSize(pageableContent.getPageable().getPageSize());
        config.setTotalPages(pageableContent.getTotalPages());
        config.setTotalElements(pageableContent.getTotalElements());

        response.setData(data);
        response.setConfig(config);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<ModelDTOResponse> get(@PathVariable String id) {
        Optional<Model> data = service.get(id);

        if (!data.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapper.fromModelToModelDTOResponse(data.get()));
    }

    @PostMapping
    @Operation(summary = "Create a new element")
    public ResponseEntity<ModelDTOResponse> create(@RequestBody @Valid ModelDTO payload) {
        Model data = service.create(mapper.fromModelDtoToModel(payload));

        return ResponseEntity.ok().body(mapper.fromModelToModelDTOResponse(data));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update element")
    public ResponseEntity<ModelDTOResponse> update(@PathVariable String id, @RequestBody @Valid ModelDTO payload) {
        Model data = service.update(id, mapper.fromModelDtoToModel(payload));

        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapper.fromModelToModelDTOResponse(data));
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
}
