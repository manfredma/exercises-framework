package hard.coded.proxy;

import dao.UserDao;

/**
 * 静态代理（硬编码代理）演示入口，手动创建代理对象并建立与目标对象的代理关系。
 * 演示最基础的代理模式实现，对比后续 JDK 动态代理和 CGLIB 代理的优势。
 */
public class App {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行的是代理的方法
    }
}