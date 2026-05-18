package manfred.exercises.framework.spring.boot.starter.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义 Spring Boot Starter 使用示例应用启动类，演示引入自定义 Starter 后的自动装配效果。
 * 配合 Test 组件在启动后验证 GreetingService Bean 是否按配置条件正确注入和执行。
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
