package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;


public interface ProjectService {
    String NAME = "workflow$ProjectService";

    List<Project> getAll();

    Project getById(UUID id);

    Project add(Project entity);

    Project update(Project entity);

    Project delete(String id);

    void setSoftDeletion(Boolean softDeletion);

}
