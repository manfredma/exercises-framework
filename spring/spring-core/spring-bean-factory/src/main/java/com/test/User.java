package com.test;

import java.util.List;

/**
 * 用户模型类，用于 BeanWrapper 和 BeanFactory 相关演示中的目标数据对象。
 * 包含姓名、年龄、邮箱、地址和爱好等字段，支持标准 getter/setter 访问。
 */
public class User {

    private String name;
    private int age;
    private String email;
    private String address;

    private List<String> hobbies;

    public User() {
    }

    public User(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}
