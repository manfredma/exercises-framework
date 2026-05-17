package top.dayarch.autoconfigure;

import org.springframework.stereotype.Component;

/**
 * 哑元邮件组件，作为 GreetingAutoConfiguration 的 @ConditionalOnClass 条件检测目标。
 * 演示自定义 Starter 中通过类存在性判断控制自动配置是否生效的条件化装配机制。
 */
@Component
public class DummyEmail {
}
