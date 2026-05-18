package manfred.spring.boot.hello.world;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Spring Boot Hello World REST 控制器，提供返回字符串、列表和日期的多个示例端点。
 * 演示 @RestController 与 @RequestMapping 的基本用法及 Spring Boot 的 JSON 自动序列化。
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello1")
    public String hello1() {
        return "Hello World";
    }

    @RequestMapping("/hello2")
    public List<String> hello2() {
        return Arrays.asList("A", "B", "C");
    }

    @RequestMapping("/2")
    public List<String> hello3() {
        return Arrays.asList("A", "B", "C");
    }

    @RequestMapping("/now")
    public Date now() {
        return new Date();
    }
}