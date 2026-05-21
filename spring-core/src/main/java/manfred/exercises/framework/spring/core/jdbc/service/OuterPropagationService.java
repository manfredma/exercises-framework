package manfred.exercises.framework.spring.core.jdbc.service;

import manfred.exercises.framework.spring.core.jdbc.model.Account;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务传播机制演示——外层服务。
 * 通过调用 InnerPropagationService 的不同传播行为方法，展示各传播级别的提交/回滚效果。
 */
@Service
public class OuterPropagationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private InnerPropagationService inner;

    /**
     * REQUIRED 场景：外层插入 id=100，内层插入 id=101 后抛异常。
     * 内外共用同一事务 → 两条记录均回滚。
     */
    @Transactional
    public void demonstrateRequired() {
        accountRepository.insert(new Account(100, "Outer-Required", 100.0));
        System.out.println("  [Outer] 插入 id=100");
        inner.insertAndThrow(new Account(101, "Inner-Required", 100.0));
    }

    /**
     * REQUIRES_NEW 场景：外层插入 id=200，调用内层独立事务插入 id=201 并提交，外层再抛异常。
     * 外层回滚 → id=200 消失；内层已独立提交 → id=201 保留。
     */
    @Transactional
    public void demonstrateRequiresNew() {
        accountRepository.insert(new Account(200, "Outer-RequiresNew", 100.0));
        System.out.println("  [Outer] 插入 id=200");
        inner.insertRequiresNew(new Account(201, "Inner-RequiresNew", 100.0));
        System.out.println("  [Outer] 抛出异常，外层回滚（内层已独立提交）");
        throw new RuntimeException("Outer REQUIRES_NEW 抛出异常");
    }

    /**
     * NESTED 场景：外层插入 id=300，调用内层 Savepoint 插入 id=301（内层抛异常回滚至 Savepoint），
     * 外层捕获后继续插入 id=302，最终提交。
     * 结果：id=300 和 id=302 保留，id=301 回滚。
     */
    @Transactional
    public void demonstrateNested() {
        accountRepository.insert(new Account(300, "Outer-Nested-1", 100.0));
        System.out.println("  [Outer] 插入 id=300");
        try {
            inner.insertNested(new Account(301, "Inner-Nested", 100.0));
        } catch (RuntimeException e) {
            System.out.println("  [Outer] 捕获内层异常（回滚至 Savepoint），外层事务继续");
        }
        accountRepository.insert(new Account(302, "Outer-Nested-2", 100.0));
        System.out.println("  [Outer] 插入 id=302，外层事务提交");
    }
}
