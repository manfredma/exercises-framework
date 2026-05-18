package manfred.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 自定义 XML 标签演示入口，通过加载 beans.xml 验证自定义命名空间解析结果。
 * 演示从 ApplicationContext 获取通过自定义标签注册的 Car Bean 并输出其属性。
 */
public class SpringCustomTagDemo {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Car car = (Car) context.getBean("car2");
        System.out.println(car);
    }
}