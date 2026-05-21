package manfred.exercises.framework.spring.core.proxy.jdk.basic;

import manfred.exercises.framework.spring.core.proxy.dao.IUserDao;
import manfred.exercises.framework.spring.core.proxy.dao.UserDao;

/**
 * JDK 动态代理演示入口，通过 ProxyFactory 在运行时为 IUserDao 接口生成代理对象。
 * 演示 JDK 动态代理要求目标类必须实现接口，并展示代理类与目标类的继承关系差异。
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        IUserDao target = new UserDao();
        System.out.println(target.getClass());

        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
