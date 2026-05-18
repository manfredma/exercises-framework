package manfred.exercises.framework.config.apollo.native_;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

import java.util.concurrent.TimeUnit;


/**
 * Apollo 原生 Java API 配置监听演示，注册 ConfigChangeListener 监听配置变更事件。
 * 展示如何在配置发生变化时获取旧值、新值及变更类型。
 */
public class ApplicationListener {

    public static void main(String[] args) throws InterruptedException {
        // config instance is singleton for each namespace and is never null
        Config config = ConfigService.getAppConfig();
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });

        TimeUnit.DAYS.sleep(1);
    }
}
