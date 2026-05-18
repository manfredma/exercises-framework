package io.github.robwin.service;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.robwin.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * Backend B 业务服务实现，以编程方式手动装饰 CircuitBreaker，并结合响应式流（Flux/Mono）操作。
 * 演示 CircuitBreakerOperator 在 Reactor 管道中的使用方式。
 */
@Service(value = "businessBService")
public class BusinessBService implements BusinessService  {

    private final Connector backendBConnector;
    private final CircuitBreaker circuitBreaker;

    public BusinessBService(@Qualifier("backendBConnector") Connector backendBConnector,
                            CircuitBreakerRegistry circuitBreakerRegistry){
        this.backendBConnector = backendBConnector;
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
    }

    public String failure() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::failure).get();
    }

    public String success() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::success).get();
    }

    @Override
    public String successException() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::successException).get();
    }

    @Override
    public String ignore() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::ignoreException).get();
    }

    @Override
    public Flux<String> fluxFailure() {
        return backendBConnector.fluxFailure()
                .transform(CircuitBreakerOperator.of(circuitBreaker));
    }

    @Override
    public Mono<String> monoSuccess() {
        return backendBConnector.monoSuccess()
                .transform(CircuitBreakerOperator.of(circuitBreaker));
    }

    @Override
    public Mono<String> monoFailure() {
        return backendBConnector.monoFailure()
                .transform(CircuitBreakerOperator.of(circuitBreaker));
    }

    @Override
    public Flux<String> fluxSuccess() {
        return backendBConnector.fluxSuccess()
                .transform(CircuitBreakerOperator.of(circuitBreaker));
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return backendBConnector.futureSuccess();
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return backendBConnector.futureFailure();
    }

    @Override
    public String failureWithFallback() {
        return backendBConnector.failureWithFallback();
    }
}
