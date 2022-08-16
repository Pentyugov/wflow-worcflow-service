package com.pentyugov.wflow.workflowservice.core.service.impl;

import com.pentyugov.wflow.workflowservice.core.service.UserSessionService;
import com.pentyugov.wflow.workflowservice.core.system.application.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pentyugov.wflow.workflowservice.core.system.application.ApplicationConstants.Roles.ADMIN;

@Service(UserSessionService.NAME)
public class UserSessionServiceImpl implements UserSessionService {


    @Override
    public Boolean isUserInRole(String roleName) {
        return getCurrentUser()
                .getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    @Override
    public Boolean isUserInAnyRole(List<String> roleNames) {
        for (String roleName : roleNames) {
            if (isUserInRole(roleName)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Boolean isUserAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN));
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
