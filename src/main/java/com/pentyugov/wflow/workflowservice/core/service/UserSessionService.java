package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.system.application.User;

import java.util.List;

public interface UserSessionService {

    String NAME = "workflow$UserService";

    Boolean isUserInRole(String roleName);
    Boolean isUserInAnyRole(List<String> roleNames);
    Boolean isUserAdmin();

    User getCurrentUser();
}
