package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.BaseDto;

public interface WflowTaskService<T extends Task, E extends BaseDto> extends EntityService<T, E> {
    String NAME = "wflow$TaskService";


}