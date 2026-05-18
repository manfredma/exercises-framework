package top.dayarch.autoconfigure;


import java.util.ArrayList;
import java.util.List;

/**
 * 问候服务类，遍历成员列表依次打印问候语，由 GreetingAutoConfiguration 自动注册为 Spring Bean。
 * 演示自定义 Starter 中核心业务服务的简单实现及与自动配置类的协作关系。
 */
public class GreetingService {

    private List<String> members = new ArrayList<>();

    public GreetingService(List<String> members) {
        this.members = members;
    }

    public void sayHello() {
        members.forEach(s -> System.out.println("hello " + s));
    }
}