package manfred.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Spring Boot Hello World 应用启动类，演示最简单的 Spring Boot Web 应用搭建方式。
 * 通过 @SpringBootApplication 一键开启自动配置、组件扫描和配置类支持。
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}