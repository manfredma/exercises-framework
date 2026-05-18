package manfred.exercises.framework.config.archaius;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import java.util.concurrent.TimeUnit;

/**
 * Netflix Archaius 动态配置演示，展示如何通过 DynamicPropertyFactory 读取配置项，
 * 并在运行时感知配置变更（无需重启即可动态刷新属性值）。
 */
public class ArchaiusConfigDemo {
    public static void main(String[] args) throws InterruptedException {
        DynamicIntProperty myAge = DynamicPropertyFactory.getInstance().getIntProperty("my.age", 18);
        System.out.println(myAge);
        System.out.println(myAge.get());

        TimeUnit.SECONDS.sleep(80);
        System.out.println("动态修改后的值为：");
        System.out.println(myAge);
        System.out.println(myAge.get());
    }
}