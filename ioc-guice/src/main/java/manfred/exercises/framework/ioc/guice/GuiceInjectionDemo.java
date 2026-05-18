package manfred.exercises.framework.ioc.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import manfred.exercises.framework.ioc.guice.impl.MyServiceImpl;

/**
 * Google Guice 依赖注入入门演示，展示 Binder/Module/Injector 三者的协作关系。
 * 演示接口绑定、对象获取及单例模式（Scopes.SINGLETON）的配置与验证。
 */
public class GuiceInjectionDemo {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        MyService myService = injector.getInstance(MyService.class);
        myService.service("Hello Guice!");

        //测试单例模式:binder.bind(MyService.class).to(MyServiceImpl.class).in(Scopes.SINGLETON);.
        MyService myService2 = injector.getInstance(MyService.class);
        System.out.println(myService.equals(myService2));//true.
    }
}
