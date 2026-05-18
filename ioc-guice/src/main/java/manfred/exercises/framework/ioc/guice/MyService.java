package manfred.exercises.framework.ioc.guice;


/**
 * Guice 依赖注入示例中的服务接口，定义单一业务方法供注入与调用。
 * 演示 Guice 通过接口绑定实现依赖解耦的核心机制。
 */
public interface MyService {

    void service(String service);
}
