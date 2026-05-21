package manfred.exercises.framework.spring.core.proxy;

import manfred.exercises.framework.spring.core.proxy.cglib.ProxyFactory;
import manfred.exercises.framework.spring.core.proxy.dao.IUserDao;
import manfred.exercises.framework.spring.core.proxy.dao.UserDao;

/**
 * CGLIB 动态代理演示入口，通过 ProxyFactory 在运行时为 UserDao 类生成子类代理。
 * 演示无需接口即可使用 CGLIB 字节码增强实现 AOP 代理的基本用法。
 */
public class CglibProxyDemo {
    public static void main(String[] args) {
        IUserDao userDao = (IUserDao) new ProxyFactory(UserDao.class).getProxyInstance();
        userDao.save();
    }
}
