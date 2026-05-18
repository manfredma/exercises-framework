package manfred.exercises.framework.spring.cloud.resilience4j.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * 业务服务接口，定义多种调用方式下的成功/失败/降级操作契约。
 * 支持同步、响应式（Mono/Flux）及异步（CompletableFuture）三类调用模式。
 */
public interface BusinessService {
    String failure();

    String success();

    String successException();

    String ignore();

    String failureWithFallback();

    Flux<String> fluxFailure();

    Mono<String> monoSuccess();

    Mono<String> monoFailure();

    Flux<String> fluxSuccess();

    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure();
}
