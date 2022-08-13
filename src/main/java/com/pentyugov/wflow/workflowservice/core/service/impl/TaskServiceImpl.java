package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.TaskDto;
import com.pentyugov.wflow.workflowservice.core.service.TaskService;
import com.pentyugov.wflow.workflowservice.core.system.config.EntityReactiveMongoTemplate;
import com.pentyugov.wflow.workflowservice.core.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service(TaskService.NAME)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService<Task, TaskDto> {

    private Boolean softDeletion = Boolean.TRUE;

    protected final EntityReactiveMongoTemplate entityReactiveMongoTemplate;

    @Override
    public Flux<Task> getAll() {
        return entityReactiveMongoTemplate.findAll(Task.class);
    }

    @Override
    public Mono<Task> getById(String id) {
        return entityReactiveMongoTemplate.findById(id, Task.class);
    }

    @Override
    public Mono<Task> add(Task entity) {
        return entityReactiveMongoTemplate.saveEntity(entity);
    }

    @Override
    public Mono<Task> update(Task entity) {
        entity.setUpdateDate(LocalDateTime.now());
        return getById(entity.getId())
                .flatMap(task -> {
                    EntityUtil.copyProperties(entity, task);
                    return entityReactiveMongoTemplate.updateEntity(task);
                });
    }

    @Override
    public Mono<Task> delete(String id) {
        if (softDeletion) {
            return entityReactiveMongoTemplate.softDelete(id, Task.class);
        }
        return entityReactiveMongoTemplate.removeById(id, Task.class);
    }

    @Override
    public void setSoftDeletion(Boolean softDeletion) {
        this.softDeletion = softDeletion;
    }

    @Override
    public Task convert(TaskDto dto) {
        return null;
    }

    @Override
    public TaskDto convert(Task task) {
        return null;
    }

}
