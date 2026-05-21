package manfred.exercises.framework.spring.core.jdbc;

import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import manfred.exercises.framework.spring.core.jdbc.service.TransferService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 数据库事务演示入口，通过转账场景展示事务提交与回滚两种行为。
 * 演示要点：
 *   1. 正常转账 → 事务提交，两端余额均变更
 *   2. 余额不足 → 抛出异常，事务回滚，余额不变
 *   3. 强制回滚 → 扣减后抛异常，验证回滚使扣减操作撤销
 */
public class TransactionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        AccountRepository repo = ctx.getBean(AccountRepository.class);
        TransferService service = ctx.getBean(TransferService.class);

        System.out.println("=== 初始余额 ===");
        repo.findAll().forEach(System.out::println);

        // 场景 1：正常转账，提交
        System.out.println("\n=== 场景 1：Alice -> Bob 转账 200，期望提交 ===");
        service.transfer(1, 2, 200.00);
        System.out.println("转账后余额：");
        repo.findAll().forEach(System.out::println);

        // 场景 2：先增加收款方，再减少付款方，最后抛异常 → 两步均回滚
        System.out.println("\n=== 场景 2：Charlie -> Alice 转账 100，先写收款方再写付款方，最后抛异常，期望两步均回滚 ===");
        System.out.println("操作前 Charlie 余额：" + repo.findById(3).getBalance()
                + "，Alice 余额：" + repo.findById(1).getBalance());
        try {
            service.transferWrongOrder(3, 1, 100.00);
        } catch (RuntimeException e) {
            System.out.println("  捕获异常：" + e.getMessage());
        }
        System.out.println("回滚后 Charlie 余额（应与操作前相同）：" + repo.findById(3).getBalance());
        System.out.println("回滚后 Alice 余额（应与操作前相同）：" + repo.findById(1).getBalance());

        // 场景 3：扣减后强制抛异常，验证回滚
        System.out.println("\n=== 场景 3：Bob -> Charlie 转账 100，扣减后强制异常，期望回滚 ===");
        System.out.println("操作前 Bob 余额：" + repo.findById(2).getBalance());
        try {
            service.transferWithRollback(2, 3, 100.00);
        } catch (RuntimeException e) {
            System.out.println("  捕获异常：" + e.getMessage());
        }
        System.out.println("回滚后 Bob 余额（应与操作前相同）：" + repo.findById(2).getBalance());

        ctx.close();
    }
}
