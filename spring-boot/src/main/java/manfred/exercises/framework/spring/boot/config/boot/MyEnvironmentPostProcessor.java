package manfred.exercises.framework.spring.boot.config.boot;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 自定义环境后处理器，实现 EnvironmentPostProcessor 在 Spring Boot 环境准备阶段介入。
 * 演示 EnvironmentPostProcessor 的执行时机及日志系统初始化前 LOGGER 无法输出的特殊现象。
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final Logger LOGGER = getLogger(MyEnvironmentPostProcessor.class);

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application) {

        System.out.printf("MyEnvironmentPostProcessor: enviroment=%s, application=%s\n", environment, application);

        // 这行无法输出是因为spring在启动的时候增加了过滤器，过略掉所有的日志输出！
        // LoggingApplicationListener.java
        // 	private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        //		this.loggingSystem = LoggingSystem.get(event.getSpringApplication().getClassLoader
        //		());
        //		this.loggingSystem.beforeInitialize();
        //	}
        LOGGER.info("enviroment={}, application={}", environment, application);
        LOGGER.error("enviroment={}, application={}", environment, application);
    }
}
