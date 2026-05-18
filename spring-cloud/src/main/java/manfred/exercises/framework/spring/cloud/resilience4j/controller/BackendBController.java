package manfred.exercises.framework.spring.cloud.resilience4j.controller;

import manfred.exercises.framework.spring.cloud.resilience4j.service.BusinessService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Backend B 的 REST 控制器（manfred resilience4j 包），演示编程式 CircuitBreaker 装饰的调用端点。
 * 通过 CircuitBreakerRegistry 动态获取熔断器实例并手动包装业务方法。
 */
@RestController
@RequestMapping(value = "/backendB")
public class BackendBController {

    private final BusinessService businessBService;

    public BackendBController(@Qualifier("businessBService")BusinessService businessBService){
        this.businessBService = businessBService;
    }

    @GetMapping("failure")
    public String backendBFailure(){
        return businessBService.failure();
    }

    @GetMapping("success")
    public String backendBSuccess(){
        return businessBService.success();
    }

    @GetMapping("ignore")
    public String ignore(){
        return businessBService.ignore();
    }
}
