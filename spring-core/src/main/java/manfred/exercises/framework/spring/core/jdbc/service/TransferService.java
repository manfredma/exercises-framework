package manfred.exercises.framework.spring.core.jdbc.service;

import manfred.exercises.framework.spring.core.jdbc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import manfred.exercises.framework.spring.core.jdbc.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 转账服务，演示 @Transactional 声明式事务的提交和回滚行为。
 * 通过转账场景验证：正常转账提交、余额不足回滚、强制异常回滚三种情况。
 */
@Service
public class TransferService {

    @Autowired
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
     * 错误顺序演示：先增加收款方，再减少付款方，最后抛异常。
     * 事务回滚后两次 updateBalance 均撤销，余额恢复原值。
     */
    @Transactional(rollbackFor = Exception.class)
    public void transferWrongOrder(int fromId, int toId, double amount) {
        Account from = accountRepository.findById(fromId);
        Account to = accountRepository.findById(toId);

        // 写操作 1：先增加收款方余额
        accountRepository.updateBalance(toId, to.getBalance() + amount);
        System.out.println("  [写1] 已增加 " + to.getName() + " 余额 " + amount
                + "，当前 DB 值：" + accountRepository.findById(toId).getBalance());

        // 写操作 2：再减少付款方余额
        accountRepository.updateBalance(fromId, from.getBalance() - amount);
        System.out.println("  [写2] 已减少 " + from.getName() + " 余额 " + amount
                + "，当前 DB 值：" + accountRepository.findById(fromId).getBalance());

        // 两次写入后抛异常，事务回滚，两步均撤销
        throw new RuntimeException("模拟异常：两次写操作均触发回滚");
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
