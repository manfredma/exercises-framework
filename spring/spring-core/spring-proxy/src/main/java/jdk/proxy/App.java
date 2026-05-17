package jdk.proxy;

import dao.IUserDao;
import dao.UserDao;

/**
 * JDK 动态代理演示入口，通过 ProxyFactory 在运行时为 IUserDao 接口生成代理对象。
 * 演示 JDK 动态代理要求目标类必须实现接口，并展示代理类与目标类的继承关系差异。
 */
public class App {
    public static void main(String[] args) {
        /* 可以输出生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true" );
         */
        // 目标对象
        IUserDao target = new UserDao();
        // 原始类型
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());
        System.out.println(proxy.getClass().getSuperclass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}