package manfred.end.spring.boot.tomcat.model;

import javax.validation.constraints.NotNull;
import manfred.end.spring.boot.tomcat.constraint.LengthConstraint;

/**
 * 用户模型类（Web Tomcat 模块），包含 email、name、address 字段。
 * email 字段使用自定义 @LengthConstraint 和 @NotNull 注解，演示 Bean Validation 在请求体中的应用。
 */
public class User {

    @LengthConstraint(min = 12, max = 15)
    @NotNull(message = "Please enter a valid email Id")
    private String email;
    private String name;
    private String address;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
