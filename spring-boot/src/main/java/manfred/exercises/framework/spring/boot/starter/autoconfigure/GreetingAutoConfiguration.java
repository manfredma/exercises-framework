package manfred.exercises.framework.spring.boot.starter.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 问候服务自动配置类，演示自定义 Spring Boot Starter 的核心实现方式。
 * 通过 @ConditionalOnProperty 和 @ConditionalOnClass 双重条件控制 GreetingService Bean 的自动注册。
 */
@Configuration
@ConditionalOnProperty(value = "rgyb.greeting.enable", havingValue = "true")
@ConditionalOnClass(DummyEmail.class)
@EnableConfigurationProperties({GreetingProperties.class})
public class GreetingAutoConfiguration {

    @Bean
    public GreetingService greetingService(GreetingProperties greetingProperties) {
        return new GreetingService(greetingProperties.getMembers());
    }
}