# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目背景

框架、中间件、稳定性相关练习项目，从 `manfredma/exercises` 迁移重构而来。
参考仓库：`manfredma/exercises-java-core`（已完成迁移重构，可作为结构标准）

## 项目结构

```
exercises-framework/
├── pom.xml             ← 根 POM，Spring Boot 2.7.18 BOM 统一管理依赖版本
├── spring-core/        ← Spring Core（IoC/Bean/代理：JDK/CGLIB/AspectJ/硬编码）
├── spring-boot/        ← Spring Boot（hello/config/h2/web-tomcat/web-jetty/starter）
├── spring-cloud/       ← Spring Cloud（Hystrix + Resilience4j 熔断/重试）
├── messaging-kafka/    ← Kafka 消息队列
├── stability/          ← 稳定性组件（Hystrix/Sentinel/Resilience4j）
├── workflow/           ← 工作流引擎（EasyFlows/EasyRules/EasyStates）
├── config/             ← 配置中心（Apollo/Archaius）
├── spring-batch/       ← Spring Batch 批处理
├── event-sourcing/     ← 事件溯源（Axon Framework）
└── ioc-guice/          ← IoC 容器（Google Guice）
```

## 包名规范

所有代码包名格式：`manfred.exercises.framework.<domain>.<topic>`

| 模块 | 包前缀 |
|------|--------|
| spring-core | `manfred.exercises.framework.spring.core` |
| spring-boot | `manfred.exercises.framework.spring.boot` |
| spring-cloud | `manfred.exercises.framework.spring.cloud` |
| messaging-kafka | `manfred.exercises.framework.messaging.kafka` |
| stability | `manfred.exercises.framework.stability` |
| workflow | `manfred.exercises.framework.workflow` |
| config | `manfred.exercises.framework.config` |
| spring-batch | `manfred.exercises.framework.batch` |
| event-sourcing | `manfred.exercises.framework.eventsourcing` |
| ioc-guice | `manfred.exercises.framework.ioc.guice` |

## 构建命令

```bash
# 编译所有模块
mvn clean compile -Dsort.skip=true

# 运行所有测试
mvn clean test -Dsort.skip=true

# 编译单个模块
mvn clean compile -pl spring-core -Dsort.skip=true

# 运行指定测试类
mvn clean test -pl stability -Dtest=HystrixTest -Dsort.skip=true
```

## 代码规范

- 包名格式：`manfred.exercises.framework.<domain>.<topic>`
- 演示类命名：`XxxDemo`（有 main 方法的演示入口）
- 不在 `src/main/java` 中使用 `@Test` 注解（例外：spring-cloud/resilience4j 的测试类暂留在 main 目录，因依赖 Spring 上下文需随应用启动）
- 所有 public 类必须有中文类级 Javadoc
- 无 `@author`/`@date` 等元数据注释
- 子模块 pom 不写 `<version>`，版本统一在根 pom 管理

## 包内分层规范

```
xxx/              ← Demo 类（有 main 方法，演示入口）
xxx.model/        ← 模型/数据类
xxx.config/       ← 配置类
xxx.service/      ← 服务类
xxx.repository/   ← 数据访问类
xxx.handler/      ← 处理器类
xxx.controller/   ← 控制器类
xxx.connector/    ← 连接器/适配器类
xxx.impl/         ← 接口实现类
```

## 添加新子模块

使用内置命令：`/init-java-exercises <module-name> [描述]`

或手动：
1. 在根目录创建模块目录及 `src/main/java/`、`src/test/java/`
2. 创建 `pom.xml`，parent 指向根 POM，依赖不写版本号
3. 在根 `pom.xml` 的 `<modules>` 中注册

子模块 pom.xml 模板：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>manfred.end</groupId>
        <artifactId>exercises-framework</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>my-module</artifactId>
    <dependencies>
        <!-- 从根 pom dependencyManagement 按需引用，不写版本号 -->
    </dependencies>
</project>
```
