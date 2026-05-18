package com.test;

/**
 * Spring Bean 循环依赖演示类 B，持有对 A 的引用并在构造和 setter 中打印调用栈。
 * 配合类 A 共同演示 Spring IoC 容器解决循环依赖的三级缓存机制。
 */
public class B {

    private A a;

    public B() {
        new RuntimeException("调用B构造方法").printStackTrace(System.out);
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        new RuntimeException("调用B的方法setA").printStackTrace(System.out);
    }


    @Override
    public String toString() {
        return "B{" +
                "a=" + System.identityHashCode(a) +
                '}';
    }
}
