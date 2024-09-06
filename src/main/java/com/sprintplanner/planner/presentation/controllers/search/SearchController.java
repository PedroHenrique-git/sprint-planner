package com.sprintplanner.planner.presentation.controllers.search;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sprintplanner.planner.domain.mapper.Mapper;
import com.sprintplanner.planner.domain.service.dto.PageableConfigDTO;
import com.sprintplanner.planner.domain.service.dto.PageableDTO;
import com.sprintplanner.planner.domain.service.search.SearchService;

import io.swagger.v3.oas.annotations.Operation;

public abstract class SearchController<SearchModel, SearchDTO, SearchDTOResponse, Service extends SearchService<SearchModel>> {
    private final Service service;
    protected final Mapper<SearchModel, SearchDTO, SearchDTOResponse> mapper;

    protected SearchController(Service service, Mapper<SearchModel, SearchDTO, SearchDTOResponse> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<SearchDTOResponse> get(@PathVariable String id) {
        Optional<SearchModel> data = service.get(id);

        if (!data.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.fromModelToModelDTOResponse(data.get()));
    }

    @GetMapping
    @Operation(summary = "Get all elements")
    protected ResponseEntity<List<SearchDTOResponse>> getAll() {
        List<SearchModel> data = service.getAll();

        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.fromListModelToListModelDTOResponse(data));
    }

    @GetMapping("/paged")
    @Operation(summary = "Get all paged elements")
    protected ResponseEntity<PageableDTO<SearchDTOResponse>> getAllPaged(
            @RequestParam(required = true, value = "page") int page,
            @RequestParam(required = true, value = "pageSize") int pageSize) {
        PageableDTO<SearchDTOResponse> response = new PageableDTO<>();
        PageableConfigDTO config = new PageableConfigDTO();

        Page<SearchModel> pageableContent = service.getAllPaged(page, pageSize);
        List<SearchDTOResponse> data = mapper.fromListModelToListModelDTOResponse(pageableContent.getContent());

        config.setPage(pageableContent.getPageable().getPageNumber());
        config.setPageSize(pageableContent.getPageable().getPageSize());
        config.setTotalPages(pageableContent.getTotalPages());
        config.setTotalElements(pageableContent.getTotalElements());

        response.setData(data);
        response.setConfig(config);

        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)).body(response);
    }
}
