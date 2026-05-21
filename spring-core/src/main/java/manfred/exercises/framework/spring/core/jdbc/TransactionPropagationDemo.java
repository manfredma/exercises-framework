package manfred.exercises.framework.spring.core.jdbc;

import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import manfred.exercises.framework.spring.core.jdbc.service.OuterPropagationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 事务传播机制演示入口，通过三个转账场景展示不同传播级别的提交/回滚效果。
 * 演示要点：
 *   REQUIRED   - 内外共用同一事务，内层抛异常 → 两条记录均回滚
 *   REQUIRES_NEW - 内层独立事务，外层抛异常 → 内层已提交的数据不受影响
 *   NESTED      - 内层使用 Savepoint，内层抛异常 → 仅回滚至 Savepoint，外层继续提交
 */
public class TransactionPropagationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        AccountRepository repo = ctx.getBean(AccountRepository.class);
        OuterPropagationService outer = ctx.getBean(OuterPropagationService.class);

        // ─── 场景 1：REQUIRED ───────────────────────────────────────────────
        System.out.println("=== 场景 1：REQUIRED（内外共用事务，内层抛异常 → 两条均回滚）===");
        try {
            outer.demonstrateRequired();
        } catch (RuntimeException e) {
            System.out.println("  捕获外层异常：" + e.getMessage());
        }
        boolean has100 = repo.findAll().stream().anyMatch(a -> a.getId() == 100);
        boolean has101 = repo.findAll().stream().anyMatch(a -> a.getId() == 101);
        System.out.println("  id=100 存在？" + has100 + "（期望 false，已回滚）");
        System.out.println("  id=101 存在？" + has101 + "（期望 false，已回滚）");

        // ─── 场景 2：REQUIRES_NEW ───────────────────────────────────────────
        System.out.println("\n=== 场景 2：REQUIRES_NEW（内层独立提交，外层回滚不影响内层）===");
        try {
            outer.demonstrateRequiresNew();
        } catch (RuntimeException e) {
            System.out.println("  捕获外层异常：" + e.getMessage());
        }
        boolean has200 = repo.findAll().stream().anyMatch(a -> a.getId() == 200);
        boolean has201 = repo.findAll().stream().anyMatch(a -> a.getId() == 201);
        System.out.println("  id=200 存在？" + has200 + "（期望 false，外层回滚）");
        System.out.println("  id=201 存在？" + has201 + "（期望 true，内层独立提交）");

        // ─── 场景 3：NESTED ─────────────────────────────────────────────────
        System.out.println("\n=== 场景 3：NESTED（Savepoint，内层抛异常仅回滚至 Savepoint）===");
        outer.demonstrateNested();
        boolean has300 = repo.findAll().stream().anyMatch(a -> a.getId() == 300);
        boolean has301 = repo.findAll().stream().anyMatch(a -> a.getId() == 301);
        boolean has302 = repo.findAll().stream().anyMatch(a -> a.getId() == 302);
        System.out.println("  id=300 存在？" + has300 + "（期望 true，外层提交）");
        System.out.println("  id=301 存在？" + has301 + "（期望 false，内层回滚至 Savepoint）");
        System.out.println("  id=302 存在？" + has302 + "（期望 true，外层提交）");

        ctx.close();
    }
}
