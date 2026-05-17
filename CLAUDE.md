# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目背景

框架、中间件、稳定性相关练习项目，从 `manfredma/exercises` 迁移重构而来。
参考仓库：`manfredma/exercises-java-core`（已完成迁移重构，可作为结构标准）

## 项目结构

```
exercises-framework/
├── pom.xml                    ← 根 POM，统一依赖版本管理（Spring Boot 2.7.18 BOM）
├── spring/                    ← Spring 全家桶
│   ├── spring-core/           ← Spring Core（IoC/AOP/代理）
│   │   ├── spring-bean-factory/   ← BeanFactory/ApplicationContext
│   │   ├── spring-custom-tag/     ← 自定义 XML 命名空间
│   │   └── spring-proxy/          ← JDK/CGLIB/Spring AOP 代理
│   ├── spring-boot/           ← Spring Boot
│   │   ├── spring-boot-hello-world/    ← 基础 REST 入门
│   │   ├── spring-boot-hello-world-v2/ ← Actuator/测试
│   │   ├── spring-boot-config/         ← 配置加载/BootstrapRegistry
│   │   ├── spring-boot-h2/             ← 内嵌 H2 数据库
│   │   ├── spring-boot-web/            ← Tomcat/Jetty 容器切换
│   │   └── spring-boot-starter/        ← 自定义 Starter 实现
│   └── spring-cloud/          ← Spring Cloud
│       └── spring-cloud-resilience/    ← Hystrix + Resilience4j
├── messaging/                 ← 消息队列
│   └── kafka/
│       ├── kafka-spring/       ← 传统 Spring 接入 Kafka
│       └── kafka-spring-boot/  ← Spring Boot + spring-kafka
├── stability/                 ← 稳定性组件
│   ├── hystrix/                ← Netflix Hystrix 熔断
│   ├── sentinel/               ← Alibaba Sentinel 限流
│   └── resilience4j/           ← Resilience4j 熔断/限流
├── workflow/                  ← 工作流引擎
│   ├── easy-flows/             ← 流程编排（顺序/并行/条件）
│   ├── easy-rule/              ← 规则引擎
│   └── easy-state/             ← 有限状态机
├── config/                    ← 配置中心
│   ├── apollo/                 ← Ctrip Apollo 配置中心
│   └── archaius/               ← Netflix Archaius 动态配置
├── batch/                     ← 批处理
│   └── spring-batch/           ← Spring Batch Job/Step/Reader/Writer
├── event-sourcing/            ← 事件溯源
│   └── axon/                   ← Axon Framework
└── ioc/                       ← IoC 容器
    └── guice/                  ← Google Guice
```

## 构建命令

```bash
# 编译所有模块
mvn clean compile -Dsort.skip=true

# 运行所有测试
mvn clean test -Dsort.skip=true

# 编译单个顶层模块
mvn clean compile -pl spring -Dsort.skip=true

# 编译单个子模块（用子模块 artifactId）
mvn clean compile -pl spring/spring-core/spring-proxy -Dsort.skip=true

# 运行指定测试类
mvn clean test -pl stability/hystrix -Dtest=HystrixTest -Dsort.skip=true
```

## 代码规范

- 包名格式：`manfred.exercises.framework.<domain>.<topic>`（迁移代码保留原包名，新增代码遵循此规范）
- 演示类命名：`XxxDemo`（有 main 方法的演示入口）
- 不在 `src/main/java` 中使用 `@Test` 注解
- 所有 public 类必须有中文类级 Javadoc
- 无 `@author`/`@date` 等元数据注释
- 子模块 pom 不写 `<version>`，版本统一在根 pom 管理

## 包内分层规范

```
xxx/              ← Demo 类（有 main 方法，演示入口）
xxx.model/        ← 模型/数据类
xxx.config/       ← 配置类
xxx.service/      ← 服务类
xxx.handler/      ← 处理器类
```

## 添加新子模块

使用内置命令：`/init-java-exercises <module-path> [描述]`

或手动：
1. 在对应领域目录下创建模块目录及 `src/main/java/`、`src/test/java/`
2. 创建 `pom.xml`，parent 指向聚合 pom，依赖不写版本号
3. 在聚合 pom 的 `<modules>` 中注册新模块

子模块 pom.xml 模板：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>manfred.end</groupId>
        <artifactId>parent-module-artifactId</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>my-module</artifactId>
    <dependencies>
        <!-- 从根 pom dependencyManagement 按需引用，不写版本号 -->
    </dependencies>
</project>
```
