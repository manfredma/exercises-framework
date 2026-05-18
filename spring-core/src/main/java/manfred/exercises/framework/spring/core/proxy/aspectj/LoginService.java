package manfred.exercises.framework.spring.core.proxy.aspectj;

/**
 * 登录服务接口，定义 login 方法作为 Spring AOP 自定义注解切面的切入点。
 */
public interface LoginService {
    String login(String userName);
}
