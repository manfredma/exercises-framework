package io.github.robwin;


import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.permanentRedirect;

/**
 * Resilience4j 示例应用启动类，基于 Spring Boot WebFlux。
 * 演示熔断器、限流器、舱壁等弹性模式的集成配置，根路径重定向到 Actuator 端点。
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> redirectRoot() {
        return route(GET("/"),
                req -> permanentRedirect(URI.create("/actuator")).build());
    }
}
