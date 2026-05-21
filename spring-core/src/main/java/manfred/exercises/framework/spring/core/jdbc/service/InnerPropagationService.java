package manfred.exercises.framework.spring.core.jdbc.service;

import manfred.exercises.framework.spring.core.jdbc.model.Account;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务传播机制演示——内层服务。
 * 提供 REQUIRED / REQUIRES_NEW / NESTED 三种传播行为的方法，供 OuterPropagationService 调用。
 */
@Service
public class InnerPropagationService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * REQUIRED（默认）：加入外层事务，内层抛异常 → 整个事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertAndThrow(Account account) {
        accountRepository.insert(account);
        System.out.println("  [Inner-REQUIRED] 插入：" + account.getName() + "，然后抛出异常");
        throw new RuntimeException("Inner REQUIRED 抛出异常，外层事务也将回滚");
    }

    /**
     * REQUIRES_NEW：挂起外层事务，内层独立提交。外层失败不影响内层已提交的数据。
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(Account account) {
        accountRepository.insert(account);
        System.out.println("  [Inner-REQUIRES_NEW] 独立事务插入：" + account.getName() + "（立即提交）");
    }

    /**
     * NESTED：在外层事务中创建 Savepoint，内层抛异常 → 回滚至 Savepoint，外层事务继续。
     */
    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(Account account) {
        accountRepository.insert(account);
        System.out.println("  [Inner-NESTED] Savepoint 插入：" + account.getName() + "，然后抛出异常");
        throw new RuntimeException("Inner NESTED 抛出异常，回滚至 Savepoint");
    }
}
