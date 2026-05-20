package manfred.exercises.framework.spring.boot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Spring Boot 配置属性绑定演示类 A，使用 @ConfigurationProperties 将 "a" 前缀属性注入字段。
 * 演示 Spring Boot 类型安全配置绑定机制，与 @Value 注入方式形成对比。
 */
@ConfigurationProperties(prefix = "a")
@Component
public class A {

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
