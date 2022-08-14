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
    public ResponseEntity<Object> getAll(@RequestHeader HttpHeaders headers) {
        UUID userId = getUserId(headers);
        return ResponseEntity
                .ok()
                .body(taskService.getAll(userId));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        UUID userId = getUserId(headers);
        return ResponseEntity
                .ok()
                .body(taskService.getById(userId, UUID.fromString(id)));
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFiltered(@RequestHeader HttpHeaders headers, @RequestBody FiltersRequest request) {
        UUID userId = getUserId(headers);
        return ResponseEntity
                .ok()
                .body(taskService.getFiltered(userId, request));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Object> getTaskHistory(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        UUID userId = getUserId(headers);
        Task task = taskService.getById(userId, UUID.fromString(id));
        return new ResponseEntity<>(taskService.getTaskHistory(userId, task), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestHeader HttpHeaders headers, @RequestBody Task task) {
        UUID userId = getUserId(headers);
        return ResponseEntity
                .ok()
                .body(taskService.add(userId, task));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestHeader HttpHeaders headers, @RequestBody Task task) {
        UUID userId = getUserId(headers);
        return ResponseEntity
                .ok()
                .body(taskService.update(userId, task));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        UUID userId = getUserId(headers);
        taskService.delete(userId, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    private UUID getUserId(HttpHeaders headers) {
        UUID userId = null;
        if (headers.get("user-id") != null) {
            String id = headers.get("user-id").get(0);
            try {
                userId = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid userId");
            }
        }
        return userId;
    }

}
