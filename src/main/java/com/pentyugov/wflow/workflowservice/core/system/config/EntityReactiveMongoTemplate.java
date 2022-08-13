package com.pentyugov.wflow.workflowservice.core.system.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class EntityReactiveMongoTemplate extends ReactiveMongoTemplate {

    public EntityReactiveMongoTemplate(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public <T extends BaseEntity> Mono<T> saveEntity(T entity) {
        entity.setCreateDate(LocalDateTime.now());
        return save(entity);
    }

    public <T extends BaseEntity> Mono<T> updateEntity(T entity) {
        entity.setUpdateDate(LocalDateTime.now());
        return save(entity);
    }

    public <T> Mono<T> removeById(Object id, Class<T> entityClass) {
        Query query = getFindByIdQuery(id);
        return findAndRemove(query, entityClass);
    }

    public <T extends BaseEntity> Mono<T> softDelete(Object id, Class<T> entityClass) {
        return findById(id, entityClass).flatMap(entity -> {
            entity.setDeleteDate(LocalDateTime.now());
            return save(entity);
        });
    }

    @Override
    public <T> Flux<T> findAll(Class<T> entityClass) {
        return find(new Query(), entityClass);
    }

    @Override
    public <T> Mono<T> findById(Object id, Class<T> entityClass) {
        Query query = getFindByIdQuery(id);
        return findOne(query, entityClass);
    }

    @Override
    public <T> Flux<T> find(Query query, Class<T> entityClass) {
        addDeleteDateIsNullCriteria(query);
        return find(query, entityClass, getCollectionName(entityClass));
    }

    @Override
    public <T> Mono<T> findOne(Query query, Class<T> entityClass) {
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
