package manfred.exercises.framework.spring.core.proxy;

import manfred.exercises.framework.spring.core.proxy.aspectj.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring AOP 自定义注解驱动演示入口，加载 XML 配置并触发带 @LoginLog 注解方法的切面拦截。
 * 演示 Spring AOP 基于自定义注解切点的完整执行流程。
 */
public class AnnotationAopDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/custom/aspectj/beans.xml");
        LoginService loginService = (LoginService) applicationContext.getBean("loginService");
        loginService.login("sdf");
    }
}
