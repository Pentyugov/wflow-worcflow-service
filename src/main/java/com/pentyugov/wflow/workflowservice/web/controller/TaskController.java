package com.pentyugov.wflow.workflowservice.web.controller;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.TaskDto;
import com.pentyugov.wflow.workflowservice.core.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService<Task, TaskDto> taskService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Task> getById(@PathVariable String id) {
        return taskService.getById(id);
    }

    @PostMapping
    public Mono<Task> add(@RequestBody Task task) {
        return taskService.add(task);
    }

    @PutMapping
    public Mono<Task> update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Task> delete(@PathVariable String id) {
        return taskService.delete(id);
    }


}
