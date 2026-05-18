package manfred.end;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 * Easy Rules 注解驱动的规则类示例，演示通过 @Rule、@Condition、@Action 定义天气提醒规则。
 * 展示注解方式相比编程式 RuleBuilder 更简洁的规则声明风格。
 */
@Rule(name = "weather rule", description = "if it rains then take an umbrella", priority = 1)
public class WeatherRule {

    @Condition
    public boolean itRains(@Fact("rain") boolean rain) {
        return rain;
    }

    @Action
    public void takeAnUmbrella() {
        System.out.println("Note: It rains, take an umbrella!");
    }
}