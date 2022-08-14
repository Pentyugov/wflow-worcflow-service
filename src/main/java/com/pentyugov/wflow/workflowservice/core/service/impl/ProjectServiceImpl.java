package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import com.pentyugov.wflow.workflowservice.core.service.ProjectService;
import com.pentyugov.wflow.workflowservice.core.system.config.EntityMongoTemplate;
import com.pentyugov.wflow.workflowservice.core.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service(ProjectService.NAME)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private Boolean softDeletion = Boolean.TRUE;

    protected final EntityMongoTemplate entityMongoTemplate;

    @Override
    public List<Project> getAll() {
        return entityMongoTemplate.findAll(Project.class);
    }

    @Override
    public Project getById(UUID id) {
        return entityMongoTemplate.findById(id, Project.class);
    }

    @Override
    public Project add(Project entity) {
        return entityMongoTemplate.saveEntity(entity);
    }

    @Override
    public Project update(Project project) {
        Project entity = getById(project.getId());
        if (entity != null) {
            EntityUtil.copyProperties(project, entity);
            entity = entityMongoTemplate.updateEntity(entity);
        }
        return entity;
    }

    @Override
    public Project delete(String id) {
        if (softDeletion) {
            return entityMongoTemplate.softDelete(id, Project.class);
        }
        return entityMongoTemplate.removeById(id, Project.class);
    }

    @Override
    public void setSoftDeletion(Boolean softDeletion) {
        this.softDeletion = softDeletion;
    }
}
