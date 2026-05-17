package nativej;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;


/**
 * Apollo 原生 Java API 配置读取演示，通过 ConfigService 获取应用默认命名空间的配置项。
 * 展示 Apollo 客户端最基础的 getProperty 用法及默认值回退机制。
 */
public class ApplicationGet {
    public static void main(String[] args) {
        // config instance is singleton for each namespace and is never null
        Config config = ConfigService.getAppConfig();

        String someKey = "test";
        String someDefaultValue = "someDefaultValueForTheKey";
        String value = config.getProperty(someKey, someDefaultValue);

        System.out.println(value);
    }
}
