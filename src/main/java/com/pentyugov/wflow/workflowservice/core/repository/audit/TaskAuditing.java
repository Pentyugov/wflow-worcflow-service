package com.pentyugov.wflow.workflowservice.core.repository.audit;

import com.pentyugov.wflow.workflowservice.core.domain.Task;
import org.springframework.data.mongodb.core.mapping.event.*;
import org.springframework.stereotype.Component;

@Component
public class TaskAuditing extends AbstractMongoEventListener<Task> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Task> event) {
        super.onBeforeConvert(event);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<Task> event) {
        super.onBeforeSave(event);

   }

}
