package manfred.exercises.framework.spring.core.jdbc.model;

/**
 * 账户模型，用于演示 JdbcTemplate CRUD 和事务转账操作。
 */
public class Account {

    private int id;
    private String name;
    private double balance;

    public Account() {}

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{id=" + id + ", name='" + name + "', balance=" + balance + '}';
    }
}
