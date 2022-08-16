package com.pentyugov.wflow.workflowservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "project")
public class Project extends Card {

    @Field(name = "name")
    private String name;

    @Field(name = "code")
    @Indexed(unique = true)
    private String code;

    @Field(name = "status")
    private Integer status;

    @Field(name = "conclusionDate")
    private Date conclusionDate;

    @Field(name = "dueDate")
    private Date dueDate;

    @Field(name = "closingDate")
    private Date closingDate;

    @Field(name = "projectManagerId")
    private String projectManagerId;

    @Field(name = "projectParticipantsIds")
    private List<UUID> projectParticipantsIds;

    @Field(name = "contractorId")
    private UUID contractorId;
}
