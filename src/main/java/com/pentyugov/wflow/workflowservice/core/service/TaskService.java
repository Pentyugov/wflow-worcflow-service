package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.CardHistory;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    String NAME = "workflow$TaskService";

    List<Task> getAll();

    List<Task> getFiltered(FiltersRequest request);

    List<CardHistory> getTaskHistory(Task task);

    Task getById(UUID id);

    Task add(Task entity);

    Task update(Task entity);

    void delete(String id);

    void setSoftDeletion(Boolean softDeletion);

}
