package io.github.robwin.exception;

/**
 * 业务异常类，用于模拟被 CircuitBreaker 忽略（不计入失败）的业务错误场景。
 * 演示 Resilience4j 中 ignoreExceptions 配置项的作用。
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
