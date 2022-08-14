package com.pentyugov.wflow.workflowservice.web.controller;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import com.pentyugov.wflow.workflowservice.core.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.pentyugov.wflow.workflowservice.core.system.application.ApplicationConstants.Routes.PROJECTS_ENDPOINT;

@RestController
@RequestMapping(PROJECTS_ENDPOINT)
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll() {
        return ResponseEntity
                .ok()
                .body(projectService.getAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(projectService.getById(UUID.fromString(id)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody Project project) {
        return ResponseEntity
                .ok()
                .body(projectService.add(project));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody Project project) {
        return ResponseEntity
                .ok()
                .body(projectService.update(project));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable String id) {
        projectService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}