package manfred.spring.cloud.resilience.resilience4j.service;


import io.vavr.control.Try;

/**
 * 业务服务接口（manfred resilience4j 包），定义失败、成功、忽略异常及带恢复的操作契约。
 * 返回 Vavr Try 类型，演示函数式错误处理与 Resilience4j 的集成模式。
 */
public interface BusinessService {
    String failure();

    String success();

    String ignore();

    Try<String> methodWithRecovery();
}
