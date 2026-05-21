# exercises-framework

> Java 框架与中间件练习项目——涵盖 Spring Core、Spring Boot、Spring Cloud、Kafka 等主流技术栈。

[![Java](https://img.shields.io/badge/Java-1.8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

[English Documentation](README.md)

---

## 项目简介

`exercises-framework` 是一个多模块 Maven 项目，用于系统性地练习和演示 Java 主流框架与中间件。每个模块独立可运行，聚焦单一技术点，通过阅读和执行演示入口类即可快速上手。

本项目从 [manfredma/exercises](https://github.com/manfredma/exercises) 迁移重构而来。  
参考仓库：[manfredma/exercises-java-core](https://github.com/manfredma/exercises-java-core)（已完成迁移，可作为结构标准）。

---

## 模块总览

| 模块 | 技术 | 主要内容 |
|------|------|---------|
| `spring-core` | Spring Framework | IoC、AOP（AspectJ / CGLIB / JDK 代理）、JdbcTemplate、声明式事务、自定义 XML 标签 |
| `spring-boot` | Spring Boot 2.7 | 自动配置、属性绑定、H2 JDBC、内嵌 Tomcat/Jetty、自定义 Starter |
| `spring-cloud` | Spring Cloud 2021 | 熔断（Hystrix + Resilience4j）、重试 |
| `messaging-kafka` | Apache Kafka 3.4 | 生产者、消费者、主题管理 |
| `stability` | Hystrix / Sentinel / Resilience4j | 熔断器、限流、重试模式 |
| `workflow` | EasyFlows / EasyRules / EasyStates | 规则引擎、状态机、流程编排 |
| `config` | Apollo / Archaius | 动态配置中心接入 |
| `spring-batch` | Spring Batch 4.3 | 批处理作业、Step、ItemReader / ItemWriter / ItemProcessor |
| `event-sourcing` | Axon Framework 4.9 | 事件溯源、CQRS、聚合根 |
| `ioc-guice` | Google Guice 5.1 | 不依赖 Spring 的依赖注入 |

---

## 环境要求

| 工具 | 版本 |
|------|------|
| Java | 1.8+ |
| Maven | 3.6+ |

> **注意：** 项目编译目标为 Java 1.8。若在 IDE 中使用 JDK 11+ 运行演示类，注入注解已统一改为 `@Autowired`，可正常使用，无需额外配置。

---

## 快速开始

### 克隆仓库

```bash
git clone https://github.com/manfredma/exercises-framework.git
cd exercises-framework
```

### 编译全部模块

```bash
mvn clean compile -Dsort.skip=true
```

### 运行演示

每个模块包含一个或多个带 `main()` 方法的演示入口类，示例：

```bash
# Spring Core — JdbcTemplate CRUD 演示
mvn exec:java -pl spring-core -Dexec.mainClass="manfred.exercises.framework.spring.core.jdbc.JdbcTemplateDemo" -Dsort.skip=true

# Spring Core — 事务提交与回滚演示
mvn exec:java -pl spring-core -Dexec.mainClass="manfred.exercises.framework.spring.core.jdbc.TransactionDemo" -Dsort.skip=true

# Spring Boot — 配置属性绑定演示（执行完自动退出）
mvn exec:java -pl spring-boot -Dexec.mainClass="manfred.exercises.framework.spring.boot.config.PropertyBindingApp" -Dsort.skip=true
```

### 运行测试

```bash
# 全部模块
mvn clean test -Dsort.skip=true

# 单个模块
mvn clean test -pl spring-boot -Dsort.skip=true
```

---

## 目录结构

```
exercises-framework/
├── pom.xml                  ← 根 POM，Spring Boot 2.7.18 BOM 统一管理依赖版本
├── spring-core/             ← Spring Core 演示
│   └── src/main/java/
│       └── .../spring/core/
│           ├── bean/        ← 启动类：BeanFactoryDemo、BeanWrapperDemo
│           │   └── circular/   ← 循环依赖 Bean 模型类
│           ├── customtag/   ← 启动类：SpringCustomTagDemo
│           │   └── impl/       ← Car、解析器、命名空间处理器
│           ├── proxy/       ← 启动类：6 个代理演示
│           │   ├── aspectj/    ← AOP 注解、切面、服务接口及实现
│           │   ├── cglib/      ← CGLIB 代理工厂
│           │   ├── dao/        ← IUserDao、UserDao
│           │   ├── hardcoded/  ← UserDaoProxy（静态代理）
│           │   ├── interceptor/← TimeCostInterceptor
│           │   └── jdk/        ← JDK 代理工厂
│           └── jdbc/        ← 启动类：JdbcTemplateDemo、TransactionDemo
│               ├── config/     ← JdbcConfig（数据源 + 事务管理器）
│               ├── model/      ← Account
│               ├── repository/ ← AccountRepository
│               └── service/    ← TransferService（@Transactional）
├── spring-boot/
├── spring-cloud/
├── messaging-kafka/
├── stability/
├── workflow/
├── config/
├── spring-batch/
├── event-sourcing/
└── ioc-guice/
```

---

## 包命名规范

所有源码包遵循如下格式：

```
manfred.exercises.framework.<domain>.<topic>
```

| 模块 | 包前缀 |
|------|--------|
| spring-core | `manfred.exercises.framework.spring.core` |
| spring-boot | `manfred.exercises.framework.spring.boot` |
| spring-cloud | `manfred.exercises.framework.spring.cloud` |
| messaging-kafka | `manfred.exercises.framework.messaging.kafka` |
| stability | `manfred.exercises.framework.stability` |

---

## 贡献指南

欢迎贡献！请按以下步骤操作：

1. Fork 本仓库
2. 创建功能分支：`git checkout -b feature/my-new-exercise`
3. 提交代码，遵循 [Conventional Commits](https://www.conventionalcommits.org/zh-hans/) 规范
4. 推送后发起 Pull Request

---

## 开源协议

本项目基于 [MIT License](LICENSE) 开源。
