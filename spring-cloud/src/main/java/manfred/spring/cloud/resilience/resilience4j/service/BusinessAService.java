package manfred.spring.cloud.resilience.resilience4j.service;

import io.vavr.control.Try;
import manfred.spring.cloud.resilience.resilience4j.connnector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Backend A 业务服务实现（manfred resilience4j 包），演示注解式 CircuitBreaker 的透明集成。
 * 通过 Vavr Try 实现函数式故障恢复，展示不依赖 Spring AOP 的降级处理方式。
 */
@Service(value = "businessAService")
public class BusinessAService implements BusinessService {

    private final Connector backendAConnector;

    public BusinessAService(@Qualifier("backendAConnector") Connector backendAConnector){
        this.backendAConnector = backendAConnector;
    }

    @Override
    public String failure() {
        return backendAConnector.failure();
    }

    @Override
    public String success() {
        return backendAConnector.success();
    }

    @Override
    public String ignore() {
        return backendAConnector.ignoreException();
    }

    @Override
    public Try<String> methodWithRecovery() {
        return Try.of(backendAConnector::failure)
                .recover((throwable) -> recovery());
    }

    private String recovery() {
        return "Hello world from recovery";
    }
}
