package com.pentyugov.wflow.workflowservice.core.system.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private UUID id;
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Role implements Serializable {

        public static final String ADMIN = "ROLE_ADMIN";

        private String name;
    }


}
