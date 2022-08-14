package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.service.UserService;
import com.pentyugov.wflow.workflowservice.core.system.application.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(UserService.NAME)
public class UserServiceImpl implements UserService {

    @Override
    public Boolean isUserInRole(User user, String roleName) {
        return user
                .getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    @Override
    public Boolean isUserInAnyRole(User user, List<String> roleNames) {
        for (String roleName : roleNames) {
            if (isUserInRole(user, roleName)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Boolean isCurrentUserAdmin(User user) {
        return user
                .getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(User.Role.ADMIN));
    }
}
