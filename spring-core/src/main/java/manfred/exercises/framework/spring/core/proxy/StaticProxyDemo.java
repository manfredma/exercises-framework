package manfred.exercises.framework.spring.core.proxy;

import manfred.exercises.framework.spring.core.proxy.dao.UserDao;
import manfred.exercises.framework.spring.core.proxy.hardcoded.UserDaoProxy;

/**
 * 静态代理（硬编码代理）演示入口，手动创建代理对象并建立与目标对象的代理关系。
 * 演示最基础的代理模式实现，对比后续 JDK 动态代理和 CGLIB 代理的优势。
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        UserDao target = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }
}
