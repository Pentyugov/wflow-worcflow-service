package com.pentyugov.wflow.workflowservice.web.controller;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.service.TaskService;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.pentyugov.wflow.workflowservice.core.system.application.ApplicationConstants.Routes.TASKS_ENDPOINT;

@RestController
@RequestMapping(TASKS_ENDPOINT)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll() {
        return ResponseEntity
                .ok()
                .body(taskService.getAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(taskService.getById(UUID.fromString(id)));
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFiltered(@RequestBody FiltersRequest request) {
        return ResponseEntity
                .ok()
                .body(taskService.getFiltered(request));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Object> getTaskHistory(@PathVariable String id) {
        Task task = taskService.getById(UUID.fromString(id));
        return new ResponseEntity<>(taskService.getTaskHistory(task), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody Task task) {
        return ResponseEntity
                .ok()
                .body(taskService.add(task));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody Task task) {
        return ResponseEntity
                .ok()
                .body(taskService.update(task));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
