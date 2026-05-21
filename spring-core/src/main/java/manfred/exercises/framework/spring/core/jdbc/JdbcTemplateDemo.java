package manfred.exercises.framework.spring.core.jdbc;

import java.util.Arrays;
import java.util.List;
import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.model.Account;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * JdbcTemplate CRUD 演示入口，展示查询、插入、更新、删除、批量插入等基本操作。
 * 使用 AnnotationConfigApplicationContext 手动构建纯 Spring（非 Boot）上下文。
 */
public class JdbcTemplateDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        AccountRepository repo = ctx.getBean(AccountRepository.class);

        // 1. 查询所有
        System.out.println("=== 1. findAll ===");
        repo.findAll().forEach(System.out::println);

        // 2. 按 ID 查询
        System.out.println("\n=== 2. findById(1) ===");
        System.out.println(repo.findById(1));

        // 3. 插入
        System.out.println("\n=== 3. insert ===");
        repo.insert(new Account(10, "Dave", 800.00));
        System.out.println("插入 Dave 后：");
        repo.findAll().forEach(System.out::println);

        // 4. 更新余额
        System.out.println("\n=== 4. updateBalance ===");
        repo.updateBalance(10, 1200.00);
        System.out.println("更新 Dave 余额后：" + repo.findById(10));

        // 5. 批量插入
        System.out.println("\n=== 5. batchInsert ===");
        List<Account> batch = Arrays.asList(
                new Account(20, "Eve",   300.00),
                new Account(21, "Frank", 150.00)
        );
        int[] results = repo.batchInsert(batch);
        System.out.println("批量插入影响行数：" + Arrays.toString(results));
        repo.findAll().forEach(System.out::println);

        // 6. 删除
        System.out.println("\n=== 6. deleteById(10) ===");
        repo.deleteById(10);
        System.out.println("删除 Dave 后：");
        repo.findAll().forEach(System.out::println);

        ctx.close();
    }
}
