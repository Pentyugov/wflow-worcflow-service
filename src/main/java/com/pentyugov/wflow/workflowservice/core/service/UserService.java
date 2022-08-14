package com.pentyugov.wflow.workflowservice.core.service;

import com.pentyugov.wflow.workflowservice.core.system.application.User;

import java.util.List;

public interface UserService {

    String NAME = "workflow$UserService";

    Boolean isUserInRole(User user, String roleName);
    Boolean isUserInAnyRole(User user, List<String> roleNames);
    Boolean isCurrentUserAdmin(User user);
}
