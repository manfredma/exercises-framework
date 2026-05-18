package manfred.exercises.framework.spring.core.proxy.cglib;

import manfred.exercises.framework.spring.core.proxy.dao.IUserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Spring CGLIB 代理演示入口，通过 Spring ApplicationContext 加载基于 CGLIB 的 AOP 代理 Bean。
 * 演示 Spring 框架自动选择 CGLIB 代理（目标类无接口时）的行为及配置方式。
 */
public class App {
    public static void main(String[] args) {
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/cglib/beans.xml");
        IUserDao userDao = context.getBean("userDao", IUserDao.class);
        userDao.save();
    }
}
