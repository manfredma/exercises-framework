package manfred.tag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Car Bean 定义解析器，继承 AbstractSimpleBeanDefinitionParser 实现自定义 XML 标签的属性解析。
 * 演示如何将 XML 元素属性（brand、color、price、maxSpeed）映射为 BeanDefinition 属性值。
 */
public class CarBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext,
                           BeanDefinitionBuilder builder) {
        String brand = element.getAttribute("brand");
        String color = element.getAttribute("color");
        double price = Double.parseDouble(element.getAttribute("price"));
        int maxSpeed = Integer.parseInt(element.getAttribute("maxSpeed"));

        if (StringUtils.hasText(brand)) {
            builder.addPropertyValue("brand", brand);
        }
        if (StringUtils.hasText(color)) {
            builder.addPropertyValue("color", color);
        }
        builder.addPropertyValue("price", price);
        builder.addPropertyValue("maxSpeed", maxSpeed);

    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Car.class;
    }
}