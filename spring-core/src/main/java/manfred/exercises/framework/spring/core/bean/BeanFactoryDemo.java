package manfred.exercises.framework.spring.core.bean;

import manfred.exercises.framework.spring.core.bean.circular.CircularDepBeanA;
import manfred.exercises.framework.spring.core.bean.circular.CircularDepBeanB;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring BeanFactory 演示类，展示通过 ClassPathXmlApplicationContext 加载 XML 配置并获取 Bean。
 * 演示 @Value 属性注入、循环依赖 Bean（CircularDepBeanA/B）的加载顺序及对象标识验证。
 */
public class BeanFactoryDemo {

    @Value("${showMsg}")
    public String showMsg;

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public static void main(String[] args) {
        BeanFactory fa = new ClassPathXmlApplicationContext("bean-factory-beans.xml");
        BeanFactoryDemo bean = fa.getBean("beanFactoryDemo", BeanFactoryDemo.class);
        System.out.println(bean.getShowMsg());

        CircularDepBeanA a = fa.getBean("a", CircularDepBeanA.class);
        CircularDepBeanB b = fa.getBean("b", CircularDepBeanB.class);

        System.out.println(System.identityHashCode(a) + ": " + a);
        System.out.println(System.identityHashCode(b) + ": " + b);
    }
}
