package manfred.end.spring.boot.tomcat.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//代表处理逻辑是MyConstraintValidator类
@Constraint(validatedBy = MyConstraintValidator.class)

/**
 * 自定义字段长度校验注解，声明 min 和 max 参数，由 MyConstraintValidator 执行实际校验逻辑。
 * 演示 Bean Validation 扩展点：自定义注解与 ConstraintValidator 的完整集成方式。
 */
public @interface LengthConstraint {

    String message() default "{too.long.or.too.short}";


    long min();

    long max();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


