package com.pentyugov.wflow.workflowservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto extends BaseDto {

    private String name;
    private String code;
    private UUID projectManagerId;
    private UUID contractorId;
    private List<UUID> participantsIds;
    private Integer status;
    private Date conclusionDate;
    private Date dueDate;
    private Date closingDate;

}
