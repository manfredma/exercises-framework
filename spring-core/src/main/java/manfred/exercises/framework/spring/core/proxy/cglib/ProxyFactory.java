package manfred.exercises.framework.spring.core.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 子类代理工厂，使用 Enhancer 在内存中动态生成目标类的子类作为代理对象。
 * 演示 CGLIB 通过字节码增强实现方法拦截，无需目标类实现任何接口。
 */
public class ProxyFactory implements MethodInterceptor {
    Class clazz;

    public ProxyFactory(Class clazz) {
        this.clazz = clazz;
    }

    public Object getProxyInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(clazz);
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始事务...");
        Object returnValue = proxy.invokeSuper(obj, args);
        System.out.println("提交事务...");
        return returnValue;
    }
}
