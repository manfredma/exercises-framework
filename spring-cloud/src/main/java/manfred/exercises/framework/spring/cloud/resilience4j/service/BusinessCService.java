package manfred.exercises.framework.spring.cloud.resilience4j.service;


import manfred.exercises.framework.spring.cloud.resilience4j.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * Backend C 业务服务实现，将请求委托给 BackendCConnector。
 * 演示通过 Connector 接口屏蔽具体弹性策略（CircuitBreaker + Retry）的服务层设计。
 */
@Service(value = "businessCService")
public class BusinessCService implements BusinessService  {

    private final Connector backendCConnector;

    public BusinessCService(@Qualifier("backendCConnector") Connector backendCConnector){
        this.backendCConnector = backendCConnector;
    }

    @Override
    public String failure() {
        return backendCConnector.failure();
    }

    @Override
    public String success() {
        return backendCConnector.success();
    }

    @Override
    public String successException() {
        return backendCConnector.successException();
    }

    @Override
    public String ignore() {
        return backendCConnector.ignoreException();
    }


    @Override
    public Flux<String> fluxFailure() {
        return backendCConnector.fluxFailure();
    }

    @Override
    public Mono<String> monoSuccess() {
        return backendCConnector.monoSuccess();
    }

    @Override
    public Mono<String> monoFailure() {
        return backendCConnector.monoFailure();
    }

    @Override
    public Flux<String> fluxSuccess() {
        return backendCConnector.fluxSuccess();
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return backendCConnector.futureSuccess();
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return backendCConnector.futureFailure();
    }

    @Override
    public String failureWithFallback() {
        return backendCConnector.failureWithFallback();
    }
}
