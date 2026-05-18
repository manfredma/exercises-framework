package manfred.end.spring.boot.jetty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Jetty 示例 REST 控制器，提供 /hello 端点并在响应中标识当前使用的嵌入式容器。
 * 演示 Spring Boot 切换为 Jetty 容器后 Web 层代码无需任何改动的容器无关性。
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot! Jetty!!";
	}

}