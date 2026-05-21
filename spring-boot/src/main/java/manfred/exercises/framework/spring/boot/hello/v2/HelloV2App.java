package manfred.exercises.framework.spring.boot.hello.v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Spring Boot Hello World v2 应用启动类，演示通过内联 @Bean CommandLineRunner 枚举容器中所有 Bean。
 * 展示与 BeanPrinterConfig 中外部 @Bean 声明的对比，验证同名 Bean 的优先级规则。
 */
@SpringBootApplication
public class HelloV2App {

    public static void main(String[] args) {
        SpringApplication.run(HelloV2App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot: C2");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
