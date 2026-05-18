package com.ilucky.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.ilucky.guice.impl.MyServiceImpl;

/**
 * Google Guice 依赖注入模块定义，将 MyService 接口绑定到 MyServiceImpl 实现并配置为单例作用域。
 * 演示 Guice Module 接口中 Binder 的接口绑定与 Scopes.SINGLETON 单例注册用法。
 */
public class MyModule implements Module {

    public void configure(Binder binder) {
        //binder.bind(MyService.class).to(MyServiceImpl.class);
        binder.bind(MyService.class).to(MyServiceImpl.class).in(Scopes.SINGLETON);
    }
}