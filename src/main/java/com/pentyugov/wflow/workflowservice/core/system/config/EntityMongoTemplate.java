package com.pentyugov.wflow.workflowservice.core.system.config;

import com.mongodb.client.MongoClient;
import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EntityMongoTemplate extends MongoTemplate {

    public EntityMongoTemplate(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public <T extends BaseEntity> T saveEntity(T entity) {
        entity.setCreateDate(LocalDateTime.now());
        entity.setId(UUID.randomUUID());
        return save(entity);
    }

    public <T extends BaseEntity> T updateEntity(T entity) {
        entity.setUpdateDate(LocalDateTime.now());
        return save(entity);
    }

    public <T> T removeById(Object id, Class<T> entityClass) {
        Query query = getFindByIdQuery(id);
        return findAndRemove(query, entityClass);
    }

    public <T extends BaseEntity> T softDelete(Object id, Class<T> entityClass) {
        T entity = findById(id, entityClass);
        if (entity != null) {
            entity.setDeleteDate(LocalDateTime.now());
        }
        return entity;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return find(new Query(), entityClass);
    }

    @Override
    public <T> T findById(Object id, Class<T> entityClass) {
        Query query = getFindByIdQuery(id);
        return findOne(query, entityClass);
    }

    @Override
    public <T> List<T> find(Query query, Class<T> entityClass) {
        addDeleteDateIsNullCriteria(query);
        return find(query, entityClass, getCollectionName(entityClass));
    }

    @Override
    public <T> T findOne(Query query, Class<T> entityClass) {
        addDeleteDateIsNullCriteria(query);
        return super.findOne(query, entityClass);
    }

    private void addDeleteDateIsNullCriteria(Query query) {
        query.addCriteria(
                Criteria.where("deleteDate").isNull()
        );
    }

    private Query getFindByIdQuery(Object id) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("_id").is(id)
        );
        return query;
    }

}
