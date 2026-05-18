package manfred.exercises.framework.spring.boot.config.boot;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;

/**
 * Bootstrap 阶段注册的早期 Bean，演示 Spring Boot 启动流程中 BootstrapRegistry 的 Bean 生命周期。
 * 通过静态初始化块观察类加载时机，验证 Bootstrap Bean 的加载顺序早于普通 Spring Bean。
 */
public class BootstrapBean {

    private static final Logger LOGGER = getLogger(BootstrapBean.class);

    static {
        System.out.println("加载俺老孙干啥！");
    }

    public void sayHello() {
        System.out.println("sayHello！");
    }
}
