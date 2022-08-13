package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.BaseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EntityService<T extends BaseEntity, E extends BaseDto> {

    Flux<T> getAll();

    Mono<T> getById(String id);

    Mono<T> add(T entity);

    Mono<T> update(T entity);

    Mono<T> delete(String id);

    void setSoftDeletion(Boolean softDeletion);

    T convert(E dto);

    E convert(Task task);
}
