package manfred.exercises.framework.spring.core.jdbc.repository;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import manfred.exercises.framework.spring.core.jdbc.model.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 账户数据访问层，演示 JdbcTemplate 的常用操作：查全部、按 ID 查、插入、更新余额、删除。
 */
@Repository
public class AccountRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Account> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM account",
                new BeanPropertyRowMapper<>(Account.class));
    }

    public Account findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM account WHERE id = ?",
                new BeanPropertyRowMapper<>(Account.class),
                id);
    }

    public int insert(Account account) {
        return jdbcTemplate.update(
                "INSERT INTO account(id, name, balance) VALUES(?, ?, ?)",
                account.getId(), account.getName(), account.getBalance());
    }

    public int updateBalance(int id, double newBalance) {
        return jdbcTemplate.update(
                "UPDATE account SET balance = ? WHERE id = ?",
                newBalance, id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM account WHERE id = ?", id);
    }

    public int[] batchInsert(List<Account> accounts) {
        List<Object[]> params = accounts.stream()
                .map(a -> new Object[]{a.getId(), a.getName(), a.getBalance()})
                .collect(Collectors.toList());
        return jdbcTemplate.batchUpdate(
                "INSERT INTO account(id, name, balance) VALUES(?, ?, ?)",
                params);
    }
}
