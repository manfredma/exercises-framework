package manfred.exercises.framework.spring.cloud.resilience4j.exception;

/**
 * 业务异常类（manfred resilience4j 包），用于模拟被 CircuitBreaker 忽略的业务错误。
 * 演示通过 ignoreExceptions 配置排除特定异常类型，使其不触发熔断计数。
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
