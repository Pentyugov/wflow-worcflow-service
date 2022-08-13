package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.dto.BaseDto;
import com.pentyugov.wflow.workflowservice.core.dto.ProjectDto;
import reactor.core.publisher.Mono;


public interface ProjectService<T extends Project, E extends ProjectDto> extends EntityService<T, E> {
    String NAME = "wflow$ProjectService";

}
