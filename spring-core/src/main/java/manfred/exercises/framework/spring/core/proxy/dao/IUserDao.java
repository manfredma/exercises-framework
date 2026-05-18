package manfred.exercises.framework.spring.core.proxy.dao;

/**
 * 用户数据访问接口，定义 save 和 save2 方法供代理演示使用。
 * 作为 JDK 动态代理和静态代理的目标接口，演示不同代理方式对接口的依赖关系。
 */
public interface IUserDao {

    void save();

    void save2();
}