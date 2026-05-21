package manfred.exercises.framework.spring.boot.config;

import static org.slf4j.LoggerFactory.getLogger;

import manfred.exercises.framework.spring.boot.config.properties.AConfigProperties;
import manfred.exercises.framework.spring.boot.config.properties.BConfigProperties;
import manfred.exercises.framework.spring.boot.config.properties.CValueProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * Spring Boot 配置演示应用启动类，对比 @ConfigurationProperties 与 @Value 两种属性注入方式。
 * 同时演示 SLF4J Logger 工厂的两种获取方式（静态工厂方法和 ILoggerFactory）。
 */
@SpringBootApplication(scanBasePackages = {"manfred.exercises.framework.spring.boot.config"})
public class PropertyBindingApp {

    private static final Logger LOGGER = getLogger(PropertyBindingApp.class);
    private static final Logger LOGGER_V2 =
            LoggerFactory.getILoggerFactory().getLogger(PropertyBindingApp.class.getName());

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PropertyBindingApp.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ApplicationContext applicationContext = app.run(args);
        LOGGER.info("V1_{}", applicationContext.getBean(AConfigProperties.class));
        LOGGER.info("V1_{}", applicationContext.getBean(BConfigProperties.class));
        LOGGER.info("V1_{}", applicationContext.getBean(CValueProperties.class));

        LOGGER_V2.info("V2_{}", applicationContext.getBean(AConfigProperties.class));
        LOGGER_V2.info("V2_{}", applicationContext.getBean(BConfigProperties.class));
        LOGGER_V2.info("V2_{}", applicationContext.getBean(CValueProperties.class));
    }

}
