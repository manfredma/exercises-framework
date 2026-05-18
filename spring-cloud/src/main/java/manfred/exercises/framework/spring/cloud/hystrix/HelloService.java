package manfred.exercises.framework.spring.cloud.hystrix;


/**
 * 问候服务接口，定义 Hystrix 熔断演示中的核心业务方法契约。
 * 实现类通过 @HystrixCommand 配置超时与熔断策略。
 */
public interface HelloService {
    String sayHello(String name);
}
