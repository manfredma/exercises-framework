package manfred.spring.cloud.resilience.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 问候服务实现，通过随机延迟模拟慢调用，演示 Hystrix 超时和熔断阈值的触发条件。
 * 配置 circuitBreaker.requestVolumeThreshold 和 execution.isolation.thread.timeoutInMilliseconds 参数。
 */
@Service
public class HelloServiceImpl implements HelloService {

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // @HystrixProperty(name = "circuitBreaker.forceOpen", value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
    @Override
    public String sayHello(String name) {
        System.out.println("async provider received: " + name);
        int x = ThreadLocalRandom.current().nextInt(400);
        try {
            TimeUnit.MILLISECONDS.sleep(x);
        } catch (InterruptedException e) {
            // e.printStackTrace();
            System.out.println("sleep interrupted!");
        }
        // throw new RuntimeException("Exception to show hystrix enabled.");
        return "hello, " + name;
    }
}