package com.pentyugov.wflow.workflowservice.core.repository;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}
