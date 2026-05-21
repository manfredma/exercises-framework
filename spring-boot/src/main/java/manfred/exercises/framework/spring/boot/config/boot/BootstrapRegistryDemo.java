package manfred.exercises.framework.spring.boot.config.boot;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * 自定义 Bootstrap 注册初始化器，演示 Spring Boot 2.4+ 引入的 BootstrapRegistryInitializer 扩展点。
 * 在应用上下文刷新前向 BootstrapRegistry 注册早期 Bean，用于观察启动阶段的初始化顺序。
 */
public class BootstrapRegistryDemo implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {
        registry.register(BootstrapBean.class, context -> new BootstrapBean());
        BootstrapBean.boot = 200;
    }
}
