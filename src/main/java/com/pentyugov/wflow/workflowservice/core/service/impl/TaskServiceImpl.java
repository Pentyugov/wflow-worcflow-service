package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.CardHistory;
import com.pentyugov.wflow.workflowservice.core.domain.Issue;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.service.FilterService;
import com.pentyugov.wflow.workflowservice.core.service.IssueService;
import com.pentyugov.wflow.workflowservice.core.service.TaskService;
import com.pentyugov.wflow.workflowservice.core.service.UserSessionService;
import com.pentyugov.wflow.workflowservice.core.system.application.User;
import com.pentyugov.wflow.workflowservice.core.system.config.EntityMongoTemplate;
import com.pentyugov.wflow.workflowservice.core.util.EntityUtil;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(TaskService.NAME)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private Boolean softDeletion = Boolean.TRUE;

    private final EntityMongoTemplate entityMongoTemplate;
    private final UserSessionService userSessionService;
    private final FilterService filterService;

    private final IssueService issueService;

    @Override
    public List<Task> getAll() {
        return entityMongoTemplate.findAll(Task.class);
    }

    private List<Task> getAllTasksForUser(User user) {

        if (userSessionService.isUserAdmin()) {
            return getAll();
        } else {
            Query query = new Query();
            query.addCriteria(
                    Criteria
                            .where("creatorId").is(user.getId())
                            .orOperator(Criteria.where("initiatorId").is(user.getId())
                                    .orOperator(Criteria.where("executorId").is(user.getId())))
            );

            return entityMongoTemplate.find(query,Task.class);
        }
    }

    @Override
    public List<Task> getFiltered(FiltersRequest request) {
        List<UUID> availableTasksIds;
        if (!CollectionUtils.isEmpty(request.getIds())) {
            availableTasksIds = request.getIds();
        } else {
            availableTasksIds = getAllTasksForUser(userSessionService.getCurrentUser())
                    .stream()
                    .map(Task::getId)
                    .collect(Collectors.toList());
        }
        Query query = filterService.createFilterQuery(availableTasksIds, request.getFilters(), Task.class);

        return entityMongoTemplate.find(query, Task.class);
    }

    @Override
    public List<CardHistory> getTaskHistory(Task task) {
        List<Issue> issues = issueService.getAllIssuesByCard(task);
        List<CardHistory> result = new ArrayList<>();
        issues.forEach(issue -> result.add(issueService.createCardHistoryDto(issue)));
        return result;
    }

    @Override
    public Task getById(UUID id) {
        return entityMongoTemplate.findById(id, Task.class);
    }

    @Override
    public Task add(Task entity) {
        return entityMongoTemplate.saveEntity(entity);
    }

    @Override
    public Task update(Task task) {
        Task entity = getById(task.getId());
        if (entity != null) {
            EntityUtil.copyProperties(task, entity);
            entity = entityMongoTemplate.updateEntity(entity);
        }
        return entity;
    }

    @Override
    public void delete(String id) {
        if (softDeletion) {
            entityMongoTemplate.softDelete(id, Task.class);
            return;
        }
        entityMongoTemplate.removeById(id, Task.class);
    }

    @Override
    public void setSoftDeletion(Boolean softDeletion) {
        this.softDeletion = softDeletion;
    }


}
