package spring;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Apollo 与 Spring Java Config 集成演示，通过 @EnableApolloConfig 启用 Apollo 配置注入。
 * 展示在纯 Spring 应用（非 Spring Boot）中接入 Apollo 的配置方式。
 */
@Configuration
@EnableApolloConfig
public class AppConfig {
    @Bean
    public TestJavaConfigBean javaConfigBean() {
        return new TestJavaConfigBean();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TestJavaConfigBean testJavaConfigBean = context.getBean(TestJavaConfigBean.class);
        System.out.println(testJavaConfigBean);
    }
}