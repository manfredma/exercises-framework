package manfred.exercises.framework.spring.boot.web.tomcat.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Web 应用初始化器，实现 WebApplicationInitializer 以编程方式注册 DispatcherServlet。
 * 演示不依赖 web.xml 的 Servlet 3.0+ 纯 Java 配置方式初始化 Spring MVC 分发器。
 */
public class ApplicationInitializer implements WebApplicationInitializer {
	 
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}