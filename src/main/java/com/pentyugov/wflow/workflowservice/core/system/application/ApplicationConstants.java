package com.pentyugov.wflow.workflowservice.core.system.application;

public class ApplicationConstants {

    public interface Routes {
        String API_URL = "/api/v1";
        String TASKS_ENDPOINT = API_URL + "/tasks";
        String PROJECTS_ENDPOINT = API_URL + "/projects";
    }

    public interface Roles {
        String ADMIN = "ROLE_ADMIN";
        String USER = "ROLE_USER";
        String SECRETARY = "ROLE_SECRETARY";
        String PROJECT_MANAGER = "ROLE_PROJECT_MANAGER";
        String PROJECT_PARTICIPANT = "ROLE_PROJECT_PARTICIPANT";
        String TASK_INITIATOR = "ROLE_TASK_INITIATOR";
        String TASK_EXECUTOR = "ROLE_TASK_EXECUTOR";
    }
}
