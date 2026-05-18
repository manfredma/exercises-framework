package manfred.exercises.framework.spring.cloud.resilience4j.connector;

import io.reactivex.Observable;

/**
 * 后端连接器接口（manfred resilience4j 包），定义同步调用和 RxJava Observable 流调用的操作契约。
 * 演示 Resilience4j 对 RxJava 响应式流的熔断保护支持。
 */
public interface Connector {
    String failure();

    String success();

    String ignoreException();

    Observable<String> methodWhichReturnsAStream();
}
