package com.pentyugov.wflow.workflowservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "issues")
public class Issue extends BaseEntity {

    @Field(name = "name")
    private String name;

    @Field(name = "result")
    private String result;

    @Field(name = "comment")
    private String comment;

    @Field(name = "userId")
    private String userId;

    @Field(name = "initiatorId")
    private String initiatorId;

    @Field(name = "executorId")
    private String executorId;

    @Field(name = "card")
    private Card card;

}