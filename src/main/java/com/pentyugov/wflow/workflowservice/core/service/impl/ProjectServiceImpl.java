package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.ProjectDto;
import com.pentyugov.wflow.workflowservice.core.service.ProjectService;
import com.pentyugov.wflow.workflowservice.core.system.config.EntityReactiveMongoTemplate;
import com.pentyugov.wflow.workflowservice.core.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service(ProjectService.NAME)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService<Project, ProjectDto> {

    private Boolean softDeletion = Boolean.TRUE;

    protected final EntityReactiveMongoTemplate entityReactiveMongoTemplate;

    @Override
    public Flux<Project> getAll() {
        return entityReactiveMongoTemplate.findAll(Project.class);
    }

    @Override
    public Mono<Project> getById(String id) {
        return entityReactiveMongoTemplate.findById(id, Project.class);
    }

    @Override
    public Mono<Project> add(Project entity) {
        return entityReactiveMongoTemplate.saveEntity(entity);
    }

    @Override
    public Mono<Project> update(Project entity) {
        entity.setUpdateDate(LocalDateTime.now());
        return getById(entity.getId())
                .flatMap(task -> {
                    EntityUtil.copyProperties(entity, task);
                    return entityReactiveMongoTemplate.updateEntity(task);
                });
    }

    @Override
    public Mono<Project> delete(String id) {
        if (softDeletion) {
            return entityReactiveMongoTemplate.softDelete(id, Project.class);
        }
        return entityReactiveMongoTemplate.removeById(id, Project.class);
    }

    @Override
    public void setSoftDeletion(Boolean softDeletion) {
        this.softDeletion = softDeletion;
    }

    @Override
    public Project convert(ProjectDto dto) {
        return null;
    }

    @Override
    public ProjectDto convert(Task task) {
        return null;
    }
}
