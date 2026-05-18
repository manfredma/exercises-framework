package manfred.exercises.framework.spring.boot.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 问候服务配置属性类，绑定 "rgyb.greeting" 前缀下的 enable 开关和 members 成员列表。
 * 演示自定义 Starter 中通过 @ConfigurationProperties 实现类型安全的外部化配置。
 */
@ConfigurationProperties(prefix = "rgyb.greeting")
public class GreetingProperties {

    /**
     * GreetingProperties 开关
     */
    boolean enable = false;

    /**
     * 需要打招呼的成员列表
     */
    List<String> members = new ArrayList<>();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}