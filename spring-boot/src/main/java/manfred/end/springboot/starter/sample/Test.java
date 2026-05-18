package manfred.end.springboot.starter.sample;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import top.dayarch.autoconfigure.GreetingService;

import javax.annotation.Resource;

/**
 * 自定义 Starter 使用验证组件，实现 InitializingBean 在 Bean 初始化完成后调用 GreetingService。
 * 演示自定义 Starter 注入的 GreetingService Bean 在 Spring 容器中正常工作的完整验证流程。
 */
@Component
public class Test implements InitializingBean {
    @Resource
    private GreetingService greetingService;


    @Override
    public void afterPropertiesSet() throws Exception {
        greetingService.sayHello();
    }
}
