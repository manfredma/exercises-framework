package manfred.end.spring.boot.tomcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC Web 配置类，注册静态资源处理器以映射 JS 和 CSS 文件的访问路径。
 * 演示通过继承 WebMvcConfigurerAdapter 扩展 Spring MVC 默认配置的传统方式。
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
		registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
	}

}