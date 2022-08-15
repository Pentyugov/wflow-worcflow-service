package com.pentyugov.wflow.workflowservice.web.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements FeignClientService {

    Logger logger = LoggerFactory.getLogger(FallBack.class);


    @Override
    public ResponseEntity<Object> getWorkflowUser() {
        return ResponseEntity.internalServerError().body("ERROR: Service is not available now");
    }
}
