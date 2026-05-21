package manfred.exercises.framework.spring.core.customtag.impl;

/**
 * 汽车模型类，作为 Spring 自定义 XML 标签演示中的目标 Bean。
 * 包含品牌、颜色、价格和最大速度字段，由自定义命名空间解析器注入属性值。
 */
public class Car {
    private int maxSpeed;
    private double price;
    private String brand;
    private String color;

    public Car() {
        System.out.println("调用Car类的无参构造函数");
    }

    public int getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "Car{maxSpeed=" + maxSpeed + ", price=" + price +
                ", brand='" + brand + "', color='" + color + "'}";
    }
}
