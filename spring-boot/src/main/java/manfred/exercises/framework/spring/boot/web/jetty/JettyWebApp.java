package manfred.exercises.framework.spring.boot.web.jetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Web（Jetty）应用启动类，演示将内嵌 Tomcat 替换为 Jetty 的配置方式。
 * 支持嵌入式 Jetty 运行和外置 WAR 包部署，展示 Spring Boot 切换嵌入式容器的灵活性。
 */
@Configuration
@ComponentScan(basePackages = "manfred.end.spring.boot.jetty")
@EnableAutoConfiguration
public class JettyWebApp extends SpringBootServletInitializer {

    private static Class applicationClass = JettyWebApp.class;

    public static void main(String[] args) {
        SpringApplication.run(JettyWebApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
