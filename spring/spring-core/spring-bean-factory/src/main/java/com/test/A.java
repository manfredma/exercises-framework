package com.test;

/**
 * Spring Bean 循环依赖演示类 A，持有对 B 的引用并在构造和 setter 中打印调用栈。
 * 演示 Spring 三级缓存如何解决 A、B 互相依赖的循环注入问题。
 */
public class A {

    private B b;

    public A () {
        new RuntimeException("调用A构造方法").printStackTrace(System.out);
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        new RuntimeException("调用A的方法setB").printStackTrace(System.out);
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + System.identityHashCode(b) +
                '}';
    }
}
