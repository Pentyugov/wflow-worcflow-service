package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.UUID;

public interface FilterService {

    String NAME = "workflow$FilterService";

    <T extends BaseEntity> Query createFilterQuery(List<UUID> cardIds, List<FiltersRequest.Filter> filters, Class<T> entityClass);
}
