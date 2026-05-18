package manfred.exercises.framework.spring.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 问候服务消费者，通过 @HystrixCommand 声明 fallback 方法实现熔断降级。
 * 演示 Hystrix 在服务调用失败或超时时自动切换到备用逻辑的核心功能。
 */
@Service
public class HelloServiceConsumer {

    @Resource
    private HelloService helloService;

    @HystrixCommand(fallbackMethod = "reliable")
    public String doSayHello(String name) {
        return helloService.sayHello(name);
    }
    public String reliable(String name) {
        return "hystrix fallback value";
    }

}
