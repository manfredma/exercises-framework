package manfred.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;


/**
 * Resilience4j 熔断器演示，展示如何通过 CircuitBreakerRegistry 创建熔断器并装饰函数调用。
 * 演示使用 Vavr 的 Try 进行函数式链式调用及熔断后的结果处理方式。
 */
public class Resilience4jCircuitBreakerDemo {
    public static void main(String[] args) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("uniqueName");

        // 用熔断器包装函数
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");

        // 链接其它的函数
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");

        // 如果函数链中的所有函数均调用成功，最终结果为Success<String>
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}
