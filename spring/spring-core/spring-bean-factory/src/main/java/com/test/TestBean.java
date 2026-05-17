package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring BeanFactory 演示类，展示通过 ClassPathXmlApplicationContext 加载 XML 配置并获取 Bean。
 * 演示 @Value 属性注入、循环依赖 Bean（A/B）的加载顺序及对象标识验证。
 */
public class TestBean {

    @Value("${showMsg}")
    public String showMsg;

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public static void main(String[] args) {
        BeanFactory fa = new ClassPathXmlApplicationContext("beans.xml");
        TestBean bean = fa.getBean("testBean", TestBean.class);
        System.out.println(bean.getShowMsg());

        A a = fa.getBean("a", A.class);
        B b = fa.getBean("b", B.class);

        System.out.println(System.identityHashCode(a) + ": " + a);
        System.out.println(System.identityHashCode(b) + ": " + b);

    }
}