package manfred.exercises.framework.spring.boot.web.tomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring MVC 视图解析器配置类，注册 InternalResourceViewResolver 以支持 JSP 视图渲染。
 * 演示通过 @Configuration 和 @Bean 方法配置视图前缀（/ui/jsp/）和后缀（.jsp）的标准方式。
 */
@Configuration
public class BeanConfig {

    @Bean
    public InternalResourceViewResolver setupViewResolver()  {
        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
        resolver.setPrefix ("/ui/jsp/");
        resolver.setSuffix (".jsp");
        resolver.setViewClass (JstlView.class);
        return resolver;
    }
}