package manfred.end.spring.boot.jetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot Web（Jetty）应用启动类，演示将内嵌 Tomcat 替换为 Jetty 的配置方式。
 * 支持嵌入式 Jetty 运行和外置 WAR 包部署，展示 Spring Boot 切换嵌入式容器的灵活性。
 */
@Configuration
@ComponentScan(basePackages = "manfred.end.spring.boot.jetty")
//@EnableWebMvc
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	
	 private static Class applicationClass = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}