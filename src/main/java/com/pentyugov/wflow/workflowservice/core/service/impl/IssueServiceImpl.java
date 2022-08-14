package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.domain.Card;
import com.pentyugov.wflow.workflowservice.core.domain.CardHistory;
import com.pentyugov.wflow.workflowservice.core.domain.Issue;
import com.pentyugov.wflow.workflowservice.core.domain.Task;
import com.pentyugov.wflow.workflowservice.core.repository.IssueRepository;
import com.pentyugov.wflow.workflowservice.core.service.IssueService;
import com.pentyugov.wflow.workflowservice.core.system.config.EntityMongoTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(IssueService.NAME)
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final EntityMongoTemplate entityMongoTemplate;
    private final IssueRepository issueRepository;

    @Override
    public Issue saveIssue(Issue issue) {
        return entityMongoTemplate.save(issue);
    }

    @Override
    public List<Card> getCardsByExecutor(String executorId) {
        return issueRepository.findCardByExecutorId(executorId, Arrays.asList(Task.STATE_ASSIGNED, Task.STATE_REWORK));
    }

    public List<Card> getAllCardsForUser(String userId) {
        return issueRepository.findCardsForUser(userId);
    }

    @Override
    public List<Card> findCardByExecutorIdAndResult(String userId, String result) {
        return issueRepository.findCardByExecutorIdAndResult(userId, result);
    }

    @Override
    public Issue createIssue(Card card, String currentUserId, String initiatorId, String executorId) {
        Issue issue = new Issue();
        issue.setCard(card);
        issue.setUserId(currentUserId);
        issue.setInitiatorId(initiatorId);
        issue.setExecutorId(executorId);
        return issue;
    }

    @Override
    public List<Issue> getAllIssuesByCard(Card card) {
        return issueRepository.findAllByCardId(card.getId());
    }

    @Override
    public CardHistory createCardHistoryDto(Issue issue) {
        CardHistory cardHistory = new CardHistory();
        cardHistory.setId(issue.getId());
        cardHistory.setCreateDate(issue.getCreateDate());
        cardHistory.setComment(issue.getComment());
        cardHistory.setUser(issue.getUserId());
        cardHistory.setResult(issue.getResult());
        return cardHistory;
    }
}
