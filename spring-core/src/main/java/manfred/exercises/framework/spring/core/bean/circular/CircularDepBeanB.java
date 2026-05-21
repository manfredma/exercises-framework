package manfred.exercises.framework.spring.core.bean.circular;

/**
 * Spring Bean 循环依赖演示类 B，持有对 CircularDepBeanA 的引用并在构造和 setter 中打印调用栈。
 * 配合 CircularDepBeanA 共同演示 Spring IoC 容器解决循环依赖的三级缓存机制。
 */
public class CircularDepBeanB {

    private CircularDepBeanA a;

    public CircularDepBeanB() {
        new RuntimeException("调用B构造方法").printStackTrace(System.out);
    }

    public CircularDepBeanA getA() {
        return a;
    }

    public void setA(CircularDepBeanA a) {
        this.a = a;
        new RuntimeException("调用B的方法setA").printStackTrace(System.out);
    }

    @Override
    public String toString() {
        return "CircularDepBeanB{a=" + System.identityHashCode(a) + '}';
    }
}
