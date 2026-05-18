package manfred.exercises.framework.config.apollo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Apollo 与 Spring XML 配置集成演示，通过 ClassPathXmlApplicationContext 加载 XML 配置文件并获取 Bean。
 * 展示 Apollo 配置项在传统 Spring XML 方式下的注入效果。
 */
public class TestXmlBean {

    private int timeout;

    private int batch;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        TestXmlBean testXmlBean = context.getBean("testXmlBean", TestXmlBean.class);
        System.out.println(testXmlBean);
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "TestXmlBean{" +
                "timeout=" + timeout +
                ", batch=" + batch +
                '}';
    }
}
