package manfred.exercises.framework.spring.core.proxy.jdk;

import manfred.exercises.framework.spring.core.proxy.dao.IUserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Spring JDK 动态代理演示入口，通过 Spring XML 配置加载基于 JDK 反射代理的 AOP Bean。
 * 演示 Spring 框架在目标类实现接口时默认选择 JDK 动态代理的行为。
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/jdk/beans.xml");
        IUserDao userDao = context.getBean("userDao", IUserDao.class);
        userDao.save();
    }
}
