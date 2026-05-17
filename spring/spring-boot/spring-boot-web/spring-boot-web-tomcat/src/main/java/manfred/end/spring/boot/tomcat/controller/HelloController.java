package manfred.end.spring.boot.tomcat.controller;

import javax.validation.Valid;
import manfred.end.spring.boot.tomcat.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Web Tomcat 示例 REST 控制器，提供普通问候和带 @Valid 请求体校验的两个端点。
 * 演示 @Valid 结合自定义 @LengthConstraint 注解在请求体参数上触发 Bean Validation 的效果。
 */
@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/index2")
    public String index2(@Valid @RequestBody User user) {
        return "Greetings from Spring Boot! (" + user.toString() + ")";
    }

}