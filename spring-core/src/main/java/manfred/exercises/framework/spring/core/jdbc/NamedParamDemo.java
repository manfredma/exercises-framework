package manfred.exercises.framework.spring.core.jdbc;

import java.util.Arrays;
import manfred.exercises.framework.spring.core.jdbc.config.JdbcConfig;
import manfred.exercises.framework.spring.core.jdbc.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * NamedParameterJdbcTemplate 演示入口。
 * 展示用 :param 具名参数替代 ? 占位符，提升 SQL 可读性，并演示多值 IN 查询和批量更新。
 */
public class NamedParamDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);
        NamedParameterJdbcTemplate namedJdbc = ctx.getBean(NamedParameterJdbcTemplate.class);

        // 1. 具名参数单条查询
        System.out.println("=== 1. 具名参数查询（:id） ===");
        MapSqlParameterSource p1 = new MapSqlParameterSource("id", 1);
        Account alice = namedJdbc.queryForObject(
                "SELECT * FROM account WHERE id = :id",
                p1, new BeanPropertyRowMapper<>(Account.class));
        System.out.println(alice);

        // 2. 多条件 + IN 查询
        System.out.println("\n=== 2. 多条件查询（:minBalance AND id IN (:ids)） ===");
        MapSqlParameterSource p2 = new MapSqlParameterSource()
                .addValue("minBalance", 400.0)
                .addValue("ids", Arrays.asList(1, 2, 3));
        namedJdbc.query(
                "SELECT * FROM account WHERE balance >= :minBalance AND id IN (:ids)",
                p2, new BeanPropertyRowMapper<>(Account.class))
                .forEach(System.out::println);

        // 3. 具名参数插入
        System.out.println("\n=== 3. 具名参数插入 ===");
        MapSqlParameterSource p3 = new MapSqlParameterSource()
                .addValue("id", 30)
                .addValue("name", "Dave")
                .addValue("balance", 900.0);
        namedJdbc.update(
                "INSERT INTO account(id, name, balance) VALUES(:id, :name, :balance)", p3);
        System.out.println("插入 Dave 后：");
        namedJdbc.query("SELECT * FROM account ORDER BY id",
                new MapSqlParameterSource(),
                new BeanPropertyRowMapper<>(Account.class))
                .forEach(System.out::println);

        // 4. 批量更新
        System.out.println("\n=== 4. 批量更新余额 ===");
        MapSqlParameterSource[] batchParams = {
                new MapSqlParameterSource().addValue("id", 1).addValue("balance", 1200.0),
                new MapSqlParameterSource().addValue("id", 2).addValue("balance", 700.0)
        };
        int[] results = namedJdbc.batchUpdate(
                "UPDATE account SET balance = :balance WHERE id = :id", batchParams);
        System.out.println("批量更新影响行数：" + Arrays.toString(results));
        namedJdbc.query("SELECT * FROM account ORDER BY id",
                new MapSqlParameterSource(),
                new BeanPropertyRowMapper<>(Account.class))
                .forEach(System.out::println);

        ctx.close();
    }
}
