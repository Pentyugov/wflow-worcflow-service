package com.pentyugov.wflow.workflowservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class Task extends Card {

    @Transient
    public static final String PRIORITY_LOW = "PRIORITY$LOW";
    @Transient
    public static final String PRIORITY_MEDIUM = "PRIORITY$MEDIUM";
    @Transient
    public static final String PRIORITY_HIGH = "PRIORITY$HIGH";

    @Transient
    public static final String STATE_ACTIVE = "TS$ACTIVE";
    @Transient
    public static final String STATE_CREATED = "TS$CREATED";
    @Transient
    public static final String STATE_ASSIGNED = "TS$ASSIGNED";
    @Transient
    public static final String STATE_FINISHED = "TS$FINISHED";
    @Transient
    public static final String STATE_CLOSED = "TS$CLOSED";
    @Transient
    public static final String STATE_CANCELED = "TS$CANCELED";
    @Transient
    public static final String STATE_EXECUTED = "TS$EXECUTED";
    @Transient
    public static final String STATE_REWORK = "TS$REWORK";

    @Transient
    public static final String KANBAN_STATE_NEW = "KB$NEW";
    @Transient
    public static final String KANBAN_STATE_IN_PROGRESS = "KB$IN_PROGRESS";
    @Transient
    public static final String KANBAN_STATE_ON_HOLD = "KB$ON_HOLD";
    @Transient
    public static final String KANBAN_STATE_COMPLETED = "KB$COMPLETED";

    @Transient
    public static final String ACTION_START = "START";
    @Transient
    public static final String ACTION_FINISH = "FINISH";
    @Transient
    public static final String ACTION_EXECUTE = "EXECUTE";
    @Transient
    public static final String ACTION_CANCEL = "CANCEL";
    @Transient
    public static final String ACTION_REWORK = "REWORK";

    @DBRef
    @Field(name = "project")
    private Project project;

    @Field(name = "priority")
    private String priority;

    @Field(name = "started")
    private Boolean started;

    @Field(name = "overdue")
    private Boolean overdue = Boolean.FALSE;

    @Field(name = "executionDatePlan")
    private Date executionDatePlan;

    @Field(name = "executionDateFact")
    private Date executionDateFact;

    @Field(name = "kanbanState")
    private String kanbanState;

    @Field(name = "kanbanOrder")
    private Integer kanbanOrder;

    @Field(name = "executorId")
    private String executorId;

    @Field(name = "initiatorId")
    private String initiatorId;
}
