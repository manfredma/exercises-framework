package manfred.end;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

/**
 * Easy Rules 规则引擎演示，展示通过 RuleBuilder 编程式定义规则和基于注解定义规则两种方式。
 * 演示 RulesEngine 根据 Facts 驱动规则匹配与动作执行的核心机制。
 */
public class EasyRuleDemo {
    public static void main(String[] args) {

        // define rules
        Rule weatherRule = new RuleBuilder()
                .name("weather rule")
                .description("if it rains then take an umbrella")
                .priority(2)
                .when(facts -> facts.get("rain").equals(true))
                .then(facts -> System.out.println("It rains, take an umbrella!"))
                .build();
        /*
         Rule weatherRule = new MVELRule()
        .name("weather rule")
        .description("if it rains then take an umbrella")
        .when("rain == true")
        .then("System.out.println(\"It rains, take an umbrella!\");");
         */
        // define facts
        Facts facts = new Facts();
        facts.put("rain", true);
        Rules rules = new Rules();
        rules.register(weatherRule);
        rules.register(new WeatherRule());

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
