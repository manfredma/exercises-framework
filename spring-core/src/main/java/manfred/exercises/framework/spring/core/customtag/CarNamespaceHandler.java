package manfred.exercises.framework.spring.core.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义 XML 命名空间处理器，注册 "car" 标签与 CarBeanDefinitionParser 的映射关系。
 * 演示 Spring 扩展点 NamespaceHandlerSupport 实现自定义 XML 命名空间的完整流程。
 */
public class CarNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("car", new CarBeanDefinitionParser());
    }
}
