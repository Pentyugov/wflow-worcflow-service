package com.pentyugov.wflow.workflowservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardHistory extends BaseEntity {
    private String comment;
    private String user;
    private String result;

}
