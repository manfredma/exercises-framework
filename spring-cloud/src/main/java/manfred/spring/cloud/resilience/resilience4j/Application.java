package manfred.spring.cloud.resilience.resilience4j;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Resilience4j 示例应用启动类（manfred 包），演示 Spring Boot 自动配置下的弹性组件集成。
 * 配合 BackendAController/BackendBController 验证熔断器和 CircuitBreakerRegistry 的使用。
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
