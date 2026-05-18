package manfred.end.spring.boot.tomcat.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义长度约束校验器，实现 ConstraintValidator 接口，验证字符串字段长度是否在 [min, max] 范围内。
 * 演示 Spring Boot 中自定义 Bean Validation 约束注解的校验逻辑实现方式。
 */
public class MyConstraintValidator implements ConstraintValidator<LengthConstraint, Object> {
    private long max = 1;
    private long min = 1;

    @Override
    public void initialize(LengthConstraint constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }

        return o.toString().trim().length() >= min && o.toString().trim().length() <= max;
    }
}