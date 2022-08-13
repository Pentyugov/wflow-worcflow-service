package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.TaskDto;

public interface TaskService<T extends Task, E extends TaskDto> extends EntityService<T, E> {
    String NAME = "wflow$TaskService";


}
