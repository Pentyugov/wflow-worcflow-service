package com.pentyugov.wflow.workflowservice.web.feign;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "wflow-main-service", fallback = FallBack.class)
public interface FeignClientService {

//    class FeignHolder {
//        public static FeignClientService create() {
//            return HystrixFeign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(ServiceFeignClient.class, "http://localhost:8081/", new FallbackFactory<ServiceFeignClient>() {
//                @Override
//                public ServiceFeignClient create(Throwable throwable) {
//                    return new ServiceFeignClient() {
//                        @Override
//                        public List<Bucket> getAllEmployeesList() {
//                            System.out.println(throwable.getMessage());
//                            return null;
//                        }
//                    };
//                }
//            });
//        }
//    }

    @RequestLine("GET /test/workflow")
    @GetMapping("/test/workflow")
    ResponseEntity<Object> getWorkflowUser();

}
