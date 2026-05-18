package manfred.exercises.framework.spring.core.proxy.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 自定义 AOP 标记注解，用于标识需要被 LogBeforeLogin 切面拦截的目标方法。
 * 演示通过自定义注解定义 @Pointcut，实现基于注解的细粒度 AOP 切点匹配。
 */
public @interface UseAop {
}