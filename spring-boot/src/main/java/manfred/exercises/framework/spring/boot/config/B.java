package manfred.exercises.framework.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * Spring Boot 配置属性绑定演示类 B，结合 @Configuration 和 @ConfigurationProperties 绑定 "b" 前缀属性。
 * 演示将配置类同时作为 Spring 配置来源与属性持有者的使用方式。
 */
@ConfigurationProperties(prefix = "b")
@Configuration
public class B {
    private int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                '}';
    }
}
