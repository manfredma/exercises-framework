package manfred.spring.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Spring Boot @Value 注入演示类，展示使用 SpEL 表达式绑定单个配置项的用法。
 * 演示多层级属性键（如 c.c2）和简单属性键的 @Value 注入方式。
 */
@Component
public class C {

    @Value("${c}")
    private int c1;
    @Value("${c.c2}")
    private String c2;
    @Value("${d}")
    private String d;

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    @Override
    public String toString() {
        return "C{" +
                "c1=" + c1 +
                ", c2='" + c2 + '\'' +
                ", d='" + d + '\'' +
                '}';
    }
}
