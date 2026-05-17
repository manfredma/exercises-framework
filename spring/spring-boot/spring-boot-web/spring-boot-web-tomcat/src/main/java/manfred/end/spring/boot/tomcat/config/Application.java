package manfred.end.spring.boot.tomcat.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot Web（Tomcat）应用启动类，支持嵌入式 Tomcat 运行和外置 WAR 包部署两种方式。
 * 演示 SpringBootServletInitializer 的 configure 方法及 @Import 导入多个配置类的用法。
 */
@Configuration
@ComponentScan(basePackages = "manfred.end.spring.boot.tomcat")
@Import({BeanConfig.class, WebConfig.class})
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