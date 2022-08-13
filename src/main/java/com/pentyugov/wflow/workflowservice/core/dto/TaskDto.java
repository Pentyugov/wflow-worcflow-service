package com.pentyugov.wflow.workflowservice.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends BaseDto {

    private ProjectDto project;
    private String priority;
    private long daysUntilDueDate;
    private String number;
    private String description;
    private String comment;
    private String state;
    private String kanbanState;
    private Integer kanbanOrder;
    private Date executionDatePlan;
    private Date executionDateFact;
    private Boolean started;
    private Boolean overdue;
    private UUID creatorId;
    private UUID executorId;
    private UUID initiatorId;

}
