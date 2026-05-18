package manfred.exercises.framework.spring.cloud.resilience4j.exception;

import java.util.function.Predicate;

/**
 * 自定义故障记录断言，用于 CircuitBreaker 判断是否将异常计为失败。
 * 演示通过 Predicate 排除 BusinessException，实现细粒度的熔断计数控制。
 */
public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        return !(throwable instanceof BusinessException);
    }
}