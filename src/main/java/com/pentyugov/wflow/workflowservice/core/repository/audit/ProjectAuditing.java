package com.pentyugov.wflow.workflowservice.core.repository.audit;

import com.pentyugov.wflow.workflowservice.core.domain.Project;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Component
public class ProjectAuditing extends AbstractMongoEventListener<Project> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Project> event) {
        event.getSource().setUpdateDate(LocalDateTime.now());
        super.onBeforeSave(event);
    }
}
