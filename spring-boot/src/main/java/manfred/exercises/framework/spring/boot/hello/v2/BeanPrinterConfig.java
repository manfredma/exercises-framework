package manfred.exercises.framework.spring.boot.hello.v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 自定义 Spring Boot 配置类，注册 CommandLineRunner Bean 用于启动时打印所有 Spring Bean 名称。
 * 演示 @Configuration 与 @Bean 声明及 ApplicationContext 的 Bean 枚举能力。
 */
@Configuration
public class BeanPrinterConfig {


    @Bean(name = "c2")
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
