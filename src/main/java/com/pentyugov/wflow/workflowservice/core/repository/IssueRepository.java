package com.pentyugov.wflow.workflowservice.core.repository;

import com.pentyugov.wflow.workflowservice.core.domain.Card;
import com.pentyugov.wflow.workflowservice.core.domain.Issue;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IssueRepository extends MongoRepository<Issue, ObjectId> {

    @Transactional(readOnly = true)
    @Query("select distinct i from issues i where i.card.id = ?1 order by i.createDate")
    List<Issue> findAllByCardId(UUID cardId);

    @Transactional(readOnly = true)
    @Query("select distinct i.card from workflow$Issue i where i.executor.id = ?1 and i.card.state in ?2")
    List<Card> findCardByExecutorId(String executorId, List<String> results);

    @Transactional(readOnly = true)
    @Query("select distinct i.card from workflow$Issue i where i.executor.id = ?1 and i.result = ?2")
    List<Card> findCardByExecutorIdAndResult(String executorId, String result);

    @Transactional(readOnly = true)
    @Query("select distinct i.card from workflow$Issue i where i.executor.id = ?1 or i.initiator.id = ?1")
    List<Card> findCardsForUser(String userId);
}
