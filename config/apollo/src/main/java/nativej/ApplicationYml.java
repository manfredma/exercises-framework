package nativej;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;


/**
 * Apollo 原生 Java API 读取自定义命名空间配置的演示，展示如何通过 ConfigService.getConfig 指定命名空间。
 * 对应 Apollo 中 application.yml 类型命名空间的配置读取方式。
 */
public class ApplicationYml {
    public static void main(String[] args) {
        Config config = ConfigService.getConfig("application");
        String someKey = "xxx";
        String someDefaultValue = "someDefaultValueForTheKey";
        String value = config.getProperty(someKey, someDefaultValue);

        System.out.println(value);

    }
}
