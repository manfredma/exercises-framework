package manfred.exercises.framework.spring.core.jdbc;

import java.util.concurrent.CountDownLatch;
import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 事务隔离级别演示入口，通过多线程并发模拟不可重复读在不同隔离级别下的表现。
 * 演示要点：
 *   READ_COMMITTED  - 同一事务内两次读取结果可能不同（不可重复读）
 *   REPEATABLE_READ - 同一事务内两次读取结果保持一致（避免不可重复读）
 */
public class IsolationLevelDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        AccountRepository repo = ctx.getBean(AccountRepository.class);
        PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class);

        // ─── 场景 1：READ_COMMITTED 下的不可重复读 ──────────────────────────
        System.out.println("=== 场景 1：READ_COMMITTED 下的不可重复读 ===");
        demonstrateNonRepeatableRead(repo, txManager, TransactionDefinition.ISOLATION_READ_COMMITTED);

        // 恢复初始余额
        repo.updateBalance(1, 1000.0);

        // ─── 场景 2：REPEATABLE_READ 避免不可重复读 ─────────────────────────
        System.out.println("\n=== 场景 2：REPEATABLE_READ 避免不可重复读 ===");
        demonstrateNonRepeatableRead(repo, txManager, TransactionDefinition.ISOLATION_REPEATABLE_READ);

        ctx.close();
    }

    /**
     * 用两个线程模拟不可重复读场景：
     *   T1：开启事务，第一次读 Alice 余额，等待 T2 提交，第二次读 Alice 余额，对比两次结果
     *   T2：更新 Alice 余额并提交
     */
    private static void demonstrateNonRepeatableRead(
            AccountRepository repo,
            PlatformTransactionManager txManager,
            int isolationLevel) throws InterruptedException {

        String levelName = isolationLevel == TransactionDefinition.ISOLATION_READ_COMMITTED
                ? "READ_COMMITTED" : "REPEATABLE_READ";

        CountDownLatch t1FirstRead = new CountDownLatch(1);   // T1 完成第一次读
        CountDownLatch t2Committed = new CountDownLatch(1);   // T2 提交完成
        CountDownLatch t1Done = new CountDownLatch(1);        // T1 完成第二次读

        // T1：在指定隔离级别事务中读取两次
        Thread t1 = new Thread(() -> {
            TransactionTemplate tx = new TransactionTemplate(txManager);
            tx.setIsolationLevel(isolationLevel);
            tx.execute(status -> {
                double first = repo.findById(1).getBalance();
                System.out.printf("  [T1-%s] 第一次读 Alice 余额：%.1f%n", levelName, first);
                t1FirstRead.countDown();          // 通知 T2 可以更新了

                try { t2Committed.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                double second = repo.findById(1).getBalance();
                System.out.printf("  [T1-%s] 第二次读 Alice 余额：%.1f%n", levelName, second);

                if (Double.compare(first, second) != 0) {
                    System.out.printf("  [T1-%s] ⚠ 不可重复读：%.1f → %.1f%n", levelName, first, second);
                } else {
                    System.out.printf("  [T1-%s] ✓ 两次读取一致（隔离级别生效）：%.1f%n", levelName, first);
                }
                t1Done.countDown();
                return null;
            });
        });

        // T2：等 T1 完成第一次读后，更新 Alice 余额并提交
        Thread t2 = new Thread(() -> {
            try { t1FirstRead.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            TransactionTemplate tx = new TransactionTemplate(txManager);
            tx.execute(status -> {
                repo.updateBalance(1, 1500.0);
                System.out.println("  [T2] 更新 Alice 余额 → 1500，提交");
                return null;
            });
            t2Committed.countDown();             // 通知 T1 可以第二次读了
        });

        t1.start();
        t2.start();
        t1Done.await();
        t1.join();
        t2.join();
    }
}
