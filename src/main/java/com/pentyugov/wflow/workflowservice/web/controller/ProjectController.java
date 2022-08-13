package com.pentyugov.wflow.workflowservice.web.controller;


import com.pentyugov.wflow.workflowservice.core.domain.Project;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.ProjectDto;
import com.pentyugov.wflow.workflowservice.core.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService<Project, ProjectDto> projectService;

    @GetMapping
    public Flux<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Project> getById(@PathVariable String id) {
        return projectService.getById(id);
    }

    @PostMapping
    public Mono<Project> add(@RequestBody Project task) {
        return projectService.add(task);
    }

    @PutMapping
    public Mono<Project> update(@RequestBody Project task) {
        return projectService.update(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Project> delete(@PathVariable String id) {
        return projectService.delete(id);
    }

    @PostMapping("/test")
    public Mono<Project> createTestData() {
        Project project = new Project();
        project.setCreatorId("06abde37-e3ed-451e-84bf-3496a4359bf1");
        project.setName("Test project");
        project.setCode("001-PR");
        project.setProjectManagerId("adf21cc7-958e-4690-bb0a-78fe1571a0a8");
        project.setContractorId("8300580d-ce06-4ab8-a803-c911652372e7");
        project.setConclusionDate(new Date());
        project.setDueDate(new Date());
        project.setClosingDate(new Date());
        project.setStatus(10);
        project.setProjectParticipantsIds(Arrays.asList("adf21cc7-958e-4690-bb0a-78fe1571a0a8", "06abde37-e3ed-451e-84bf-3496a4359bf1"));

        return projectService.add(project);
    }


}
