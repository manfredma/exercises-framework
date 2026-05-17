package io.github.robwin.connnector;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * 后端连接器接口，定义同步、响应式及异步多种调用方式下的成功/失败/降级操作契约。
 * 各实现类通过注解或编程式方式集成 Resilience4j 弹性策略。
 */
public interface Connector {
    String failure();

    String success();

    String successException();

    String ignoreException();

    Flux<String> fluxFailure();

    Mono<String> monoSuccess();

    Mono<String> monoFailure();

    Flux<String> fluxSuccess();

    String failureWithFallback();

    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure();

}
