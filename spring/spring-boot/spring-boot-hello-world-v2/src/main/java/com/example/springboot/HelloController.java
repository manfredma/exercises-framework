package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring Boot v2 示例 REST 控制器，提供根路径的简单问候响应。
 * 演示 @RestController 与 @RequestMapping 的最基础用法。
 */
@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}