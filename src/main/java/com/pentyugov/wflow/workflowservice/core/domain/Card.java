package com.pentyugov.wflow.workflowservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

    @Field(name = "number")
    private String number;

    @Field(name = "description")
    private String description;

    @Field(name = "comment")
    private String comment;

    @Field(name = "state")
    private String state;

    @Field(name = "parentCard")
    private Card parentCard;

    @Field(name = "creatorId")
    private String creatorId;

    @Field(name = "issue")
    private Issue issue;

}
