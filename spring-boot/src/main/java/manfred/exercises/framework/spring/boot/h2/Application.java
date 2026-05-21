package manfred.exercises.framework.spring.boot.h2;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import manfred.exercises.framework.spring.boot.h2.model.Employee;
import manfred.exercises.framework.spring.boot.h2.repository.EmployeeJDBCRepository;


/**
 * Spring Boot H2 内存数据库演示应用，实现 CommandLineRunner 在启动后执行 JDBC CRUD 操作。
 * 演示 Spring Boot 自动配置 H2 数据源及 JdbcTemplate 进行增删改查和批量插入的使用方式。
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmployeeJDBCRepository employeeRepository;

    @Override
    public void run(String... args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10011L, "Ramesh", "Fadatare", "ramesh@gmail.com"));
        employees.add(new Employee(10021L, null, "Fadatare", "ramesh@gmail.com"));
        LOGGER.info("Inserting -> {}", employeeRepository.batchInsert(employees));
        LOGGER.info("Inserting -> {}", employeeRepository.insert(new Employee(10012L, "John",
                "Cena", "john@gmail.com")));
        LOGGER.info("Inserting -> {}", employeeRepository.insert(new Employee(10013L, "tony",
                "stark", "stark@gmail.com")));

        LOGGER.info("Employee id 10011 -> {}", employeeRepository.findById(10011L));

        LOGGER.info("Update 10003 -> {}", employeeRepository.update(new Employee(10011L, "ram",
                "Stark", "ramesh123@gmail.com")));

        employeeRepository.deleteById(10013L);

        LOGGER.info("All users -> {}", employeeRepository.findAll());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}