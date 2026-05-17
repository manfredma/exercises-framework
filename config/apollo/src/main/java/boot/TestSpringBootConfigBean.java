package boot;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Apollo 配置中心与 Spring Boot 集成演示 Bean，通过 @Value 注入 Apollo 中的配置项。
 * 实现 InitializingBean 以在初始化后打印配置值，验证动态配置是否生效。
 */
@Component
public class TestSpringBootConfigBean implements InitializingBean {

    private static final Logger LOGGER = getLogger(TestSpringBootConfigBean.class);

    @Value("${timeout:100}")
    private int timeout;

    @Value("${batch:200}")
    private int batch;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.error("TestSpringBootConfigBean initialized " + this);
    }

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
