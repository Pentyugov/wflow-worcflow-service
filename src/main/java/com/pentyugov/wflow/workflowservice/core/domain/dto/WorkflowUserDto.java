package com.pentyugov.wflow.workflowservice.core.domain.dto;

import java.util.UUID;

public class WorkflowUserDto {

    private UUID id;
    private String username;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
