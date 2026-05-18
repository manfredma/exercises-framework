package manfred.exercises.framework.spring.core.proxy.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

/**
 * 登录前置日志切面，使用 @Aspect 和 @Before 注解在 @UseAop 标注的方法执行前织入日志逻辑。
 * 演示 Spring AOP 自定义注解驱动切点的定义方式及 JoinPoint 参数的使用。
 */
@Aspect
public class LogBeforeLogin {

    @Resource
    private LoginService loginService;

    private String name;
	
    @Pointcut("@annotation(spring.custom.aspectj.UseAop)")
    public void xxx(){}

    @Before("xxx()")
    public void beforeLogin(JoinPoint joinPoint){
        System.out.println("有人要登录了。。。, interceptor name = " + name);
        System.out.println("before " + joinPoint);
    }

    public void setName(String name) {
        this.name = name;
    }
}