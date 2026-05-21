package manfred.exercises.framework.spring.core.jdbc.service;

import javax.annotation.Resource;
import manfred.exercises.framework.spring.core.jdbc.model.Account;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 转账服务，演示 @Transactional 声明式事务的提交和回滚行为。
 * 通过转账场景验证：正常转账提交、余额不足回滚、强制异常回滚三种情况。
 */
@Service
public class TransferService {

    @Resource
    private AccountRepository accountRepository;

    /**
     * 正常转账：扣减转出方余额，增加转入方余额，全部成功则提交。
     */
    @Transactional
    public void transfer(int fromId, int toId, double amount) {
        Account from = accountRepository.findById(fromId);
        Account to = accountRepository.findById(toId);

        if (from.getBalance() < amount) {
            throw new RuntimeException("余额不足：" + from.getName() + " 当前余额 " + from.getBalance());
        }

        accountRepository.updateBalance(fromId, from.getBalance() - amount);
        accountRepository.updateBalance(toId, to.getBalance() + amount);
        System.out.printf("  转账成功：%s -> %s，金额 %.2f%n", from.getName(), to.getName(), amount);
    }

    /**
     * 演示回滚：扣减转出方之后抛出异常，事务回滚，余额恢复原值。
     */
    @Transactional(rollbackFor = Exception.class)
    public void transferWithRollback(int fromId, int toId, double amount) {
        Account from = accountRepository.findById(fromId);
        accountRepository.updateBalance(fromId, from.getBalance() - amount);
        System.out.println("  已扣减 " + from.getName() + " 余额 " + amount + "，即将抛出异常触发回滚...");
        // 模拟业务异常，事务回滚，上面的 updateBalance 不会生效
        throw new RuntimeException("模拟业务异常，事务回滚");
    }
}
