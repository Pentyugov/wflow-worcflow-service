package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.CardHistory;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    String NAME = "workflow$TaskService";

    List<Task> getAll(UUID userId);

    List<Task> getFiltered(UUID userId, FiltersRequest request);

    List<CardHistory> getTaskHistory(UUID userId, Task task);

    Task getById(UUID userId, UUID id);

    Task add(UUID userId, Task entity);

    Task update(UUID userId, Task entity);

    Task delete(UUID userId, String id);

    void setSoftDeletion(Boolean softDeletion);

}
