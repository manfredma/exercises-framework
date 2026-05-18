package manfred.spring.cloud.resilience.resilience4j.controller;

import manfred.spring.cloud.resilience.resilience4j.service.BusinessService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Backend A 的 REST 控制器（manfred resilience4j 包），提供失败、成功、忽略异常及带恢复的端点。
 * 演示注解式 CircuitBreaker 与 Vavr Try 函数式恢复在 Web 层的集成。
 */
@RestController
@RequestMapping(value = "/backendA")
public class BackendAController {

    private final BusinessService businessAService;

    public BackendAController(@Qualifier("businessAService") BusinessService businessAService){
        this.businessAService = businessAService;
    }

    @GetMapping("failure")
    public String failure(){
        return businessAService.failure();
    }

    @GetMapping("success")
    public String success(){
        return businessAService.success();
    }

    @GetMapping("ignore")
    public String ignore(){
        return businessAService.ignore();
    }

    @GetMapping("recover")
    public String methodWithRecovery(){
        return businessAService.methodWithRecovery().get();
    }
}
