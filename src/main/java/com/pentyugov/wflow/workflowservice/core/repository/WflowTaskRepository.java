package com.pentyugov.wflow.workflowservice.core.repository;

import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public class WflowTaskRepository<T extends BaseEntity, ID extends Serializable> extends SimpleReactiveMongoRepository<T, ID> {

    public WflowTaskRepository(MongoEntityInformation<T, ID> entityInformation, ReactiveMongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
    }

    @Override
    public Mono<T> findById(Publisher<ID> publisher) {
        return super.findById(publisher);
    }


}
