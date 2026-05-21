package manfred.exercises.framework.spring.core.jdbc;

import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.model.Account;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 编程式事务（TransactionTemplate）演示入口。
 * 对比声明式 @Transactional，展示手动控制事务提交、回滚和只读的三种方式。
 */
public class ProgrammaticTxDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        AccountRepository repo = ctx.getBean(AccountRepository.class);
        PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class);
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);

        // 场景 1：正常提交
        System.out.println("=== 场景 1：TransactionTemplate 正常提交 ===");
        txTemplate.execute(status -> {
            repo.insert(new Account(50, "Prog-Commit", 500.0));
            System.out.println("  插入 id=50 Prog-Commit");
            return null;
        });
        boolean has50 = repo.findAll().stream().anyMatch(a -> a.getId() == 50);
        System.out.println("  提交后 id=50 存在？" + has50 + "（期望 true）");

        // 场景 2：setRollbackOnly 手动回滚
        System.out.println("\n=== 场景 2：setRollbackOnly 手动标记回滚 ===");
        txTemplate.execute(status -> {
            repo.insert(new Account(51, "Prog-Rollback", 600.0));
            System.out.println("  插入 id=51 Prog-Rollback，调用 setRollbackOnly()");
            status.setRollbackOnly();
            return null;
        });
        boolean has51 = repo.findAll().stream().anyMatch(a -> a.getId() == 51);
        System.out.println("  回滚后 id=51 存在？" + has51 + "（期望 false）");

        // 场景 3：异常触发回滚
        System.out.println("\n=== 场景 3：RuntimeException 触发自动回滚 ===");
        try {
            txTemplate.execute(status -> {
                repo.insert(new Account(52, "Prog-Exception", 700.0));
                System.out.println("  插入 id=52 Prog-Exception，然后抛异常");
                throw new RuntimeException("模拟业务异常");
            });
        } catch (RuntimeException e) {
            System.out.println("  捕获异常：" + e.getMessage());
        }
        boolean has52 = repo.findAll().stream().anyMatch(a -> a.getId() == 52);
        System.out.println("  回滚后 id=52 存在？" + has52 + "（期望 false）");

        // 场景 4：只读事务
        System.out.println("\n=== 场景 4：只读事务（readOnly=true） ===");
        txTemplate.setReadOnly(true);
        txTemplate.execute(status -> {
            System.out.println("  只读事务查询，共 " + repo.findAll().size() + " 条记录");
            return null;
        });

        ctx.close();
    }
}
