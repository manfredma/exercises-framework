package manfred.exercises.framework.spring.core.proxy.dao;

/**
 * 用户数据访问实现类，作为各类代理演示中的目标对象（被代理类）。
 * 演示 save 调用 save2 的内部调用场景，用于对比不同代理方式对内部调用的增强效果差异。
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println(
                this.getClass() + " - " + System.identityHashCode(this) + ": ----已经保存数据!----1");
        // new Throwable().printStackTrace(System.out);
        save2();
    }

    @Override
    public void save2() {
        System.out.println(
                this.getClass() + " - " + System.identityHashCode(this) + ": ----已经保存数据!----2");
        // new Throwable().printStackTrace(System.out);
    }
}