package manfred.exercises.framework.batch.model;

/**
 * 员工数据模型，用于 Spring Batch 批处理流程中 CSV 文件行到 Java 对象的映射。
 * 包含姓名、年龄和薪资字段，供 ItemReader 反序列化使用。
 */
public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}