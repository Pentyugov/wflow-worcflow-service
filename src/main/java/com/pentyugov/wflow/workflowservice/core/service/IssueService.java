package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.domain.Card;
import com.pentyugov.wflow.workflowservice.core.domain.CardHistory;
import com.pentyugov.wflow.workflowservice.core.domain.Issue;
import com.pentyugov.wflow.workflowservice.core.system.application.User;

import java.util.List;

public interface IssueService {

    String NAME = "workflow$IssueService";

    Issue saveIssue(Issue issue);

    List<Card> getCardsByExecutor(String executorId);

    List<Card> getAllCardsForUser(String userId);

    List<Card> findCardByExecutorIdAndResult(String userId, String result);

    Issue createIssue(Card card, String currentUserId, String initiatorId, String executorId);

    List<Issue> getAllIssuesByCard(Card card);

    CardHistory createCardHistoryDto(Issue issue);
}

