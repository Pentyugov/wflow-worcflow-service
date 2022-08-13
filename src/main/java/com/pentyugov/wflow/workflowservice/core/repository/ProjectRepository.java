package com.pentyugov.wflow.workflowservice.core.repository;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectRepository extends ReactiveMongoRepository<Project, String> {
    @Override
    Mono<Void> deleteById(String s);
}
