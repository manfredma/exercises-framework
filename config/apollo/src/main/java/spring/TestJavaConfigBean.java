package spring;

import org.springframework.beans.factory.annotation.Value;


/**
 * Apollo 与 Spring Java Config 集成的配置 Bean，通过 @Value 注解绑定 Apollo 中的配置项。
 * 演示 timeout 和 batch 两个参数的动态注入及默认值回退。
 */
public class TestJavaConfigBean {

    @Value("${timeout:100}")
    private int timeout;

    @Value("${batch:200}")
    private int batch;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
