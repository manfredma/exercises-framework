package manfred.exercises.framework.ioc.guice.impl;

import manfred.exercises.framework.ioc.guice.MyService;

/**
 * MyService 接口的 Guice 注入实现类，接收并打印服务调用参数。
 * 作为 Guice 绑定的目标实现，演示接口与实现分离的依赖注入效果。
 */
public class MyServiceImpl implements MyService {

    public void service(String service) {
        System.out.println("===>" + service);
    }
}