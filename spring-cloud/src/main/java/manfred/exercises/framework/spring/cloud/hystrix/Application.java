package manfred.exercises.framework.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;


/**
 * Hystrix 熔断器示例应用入口，演示 @EnableHystrix 开启熔断支持。
 * 通过循环调用模拟高并发场景，观察熔断器触发与 fallback 降级的效果。
 */
@SpringBootApplication(scanBasePackages = {"manfred.spring.cloud.resilience.hystrix"})
@EnableHystrix
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        HelloServiceConsumer helloServiceConsumer = applicationContext.getBean(HelloServiceConsumer.class);
        int successCount = 0;
        int fail = 0;
        for (int i = 0; i < 100; i++) {
            try {
                String x = helloServiceConsumer.doSayHello("xxx");
                if ("hystrix fallback value".equals(x)) {
                    fail++;
                } else {
                    successCount++;
                }
                System.out.println("t=" + i +", s=" + successCount + ", f=" + fail + ": " + helloServiceConsumer.doSayHello("xxx"));
            } catch (Exception e) {
                System.out.println(i + " : " + e);
            }
        }
    }
}
