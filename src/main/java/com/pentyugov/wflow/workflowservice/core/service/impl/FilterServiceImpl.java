package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import com.pentyugov.wflow.workflowservice.core.service.FilterService;
import com.pentyugov.wflow.workflowservice.web.payload.FiltersRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service(FilterService.NAME)
public class FilterServiceImpl implements FilterService {

    @Override
    public <T extends BaseEntity> Query createFilterQuery(List<UUID> cardIds, List<FiltersRequest.Filter> filters, Class<T> entityClass) {
        Query query = new Query();

        if (!CollectionUtils.isEmpty(cardIds)) {
            query.addCriteria(Criteria.where("_id").in(cardIds));
        }

        for (FiltersRequest.Filter filter : filters) {
            if (StringUtils.hasText(filter.getProperty()) && StringUtils.hasText((filter.getCondition()))) {
                Field field = getProperty(entityClass, filter.getProperty());

                if (field != null) {
                    boolean assignableFrom = BaseEntity.class.isAssignableFrom(field.getType());
                    if (assignableFrom) {
                        query.addCriteria(
                                Criteria
                                        .where(filter.getProperty() + ".$id")
                                        .in(Collections.singletonList(UUID.fromString(filter.getCondition())))
                        );
                    } else {
                        query.addCriteria(
                                Criteria
                                        .where(filter.getProperty())
                                        .is(filter.getProperty().toLowerCase().endsWith("id") ?
                                                UUID.fromString(filter.getCondition()) : filter.getCondition())
                        );
                    }
                }
            }
        }

        return query;
    }

    private Field getProperty(Class<?> clazz, String property) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(property);
        } catch (NoSuchFieldException ignored) {
            if (clazz.getSuperclass() != null) {
                field = getProperty(clazz.getSuperclass(), property);
            }
        }

        return field;
    }
}
