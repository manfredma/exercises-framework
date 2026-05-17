package com.test;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

/**
 * BeanWrapper 使用演示类，展示 Spring BeanWrapperImpl 对普通 Java 对象的属性动态赋值能力。
 * 演示无需直接调用 setter 即可通过反射机制设置对象属性的 Spring 底层工具用法。
 */
public class TestBeanWrapper {
    public static void main(String[] args) {
        BeanWrapper bw = new BeanWrapperImpl(User.class);
        bw.setPropertyValue("name", "maxingfang");
        bw.setPropertyValue("age", 25);
        bw.setPropertyValue("hobbies", Arrays.asList("2", "3"));
        User user = (User) bw.getWrappedInstance();
        System.out.println(user);
    }
}
