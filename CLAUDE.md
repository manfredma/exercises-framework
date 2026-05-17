# exercises-framework

框架、中间件、稳定性相关练习项目，从 `manfredma/exercises` 迁移重构而来。

## 项目背景

本项目从 `manfredma/exercises` 拆分，对应原项目中以下模块：

| 原模块 | 迁移目标模块 | 说明 |
|--------|-------------|------|
| `framework/spring/` | `spring/` | Spring 全家桶（Core/Boot/Cloud/MVC） |
| `framework/mq/` | `messaging/` | 消息队列（Kafka、MQ 基础） |
| `framework/axon/` | `event-sourcing/` | Axon 框架、事件溯源 |
| `framework/guice/` | `ioc/guice/` | Google Guice 依赖注入 |
| `config/` | `config/` | 配置中心（Apollo、Archaius） |
| `workflow/` | `workflow/` | 工作流（EasyFlow、EasyRule、EasyState） |
| `stability/` | `stability/` | 稳定性（Hystrix、Sentinel、Resilience4j） |
| `batch_processing/` | `batch/` | 批处理（Spring Batch） |

参考仓库：`manfredma/exercises-java-core`（已完成迁移重构，可作为结构标准）

## 目标项目结构

```
exercises-framework/
├── pom.xml
├── spring/               ← Spring 全家桶
│   ├── spring-core/      ← Spring Core（IoC/AOP）
│   ├── spring-boot/      ← Spring Boot
│   ├── spring-mvc/       ← Spring MVC
│   └── spring-cloud/     ← Spring Cloud
├── messaging/            ← 消息队列
│   └── kafka/            ← Kafka
├── stability/            ← 稳定性组件
│   ├── hystrix/
│   ├── sentinel/
│   └── resilience4j/
├── config/               ← 配置中心
├── workflow/             ← 工作流引擎
└── batch/                ← 批处理
```

## 迁移说明

- 源仓库：`git@github.com:manfredma/exercises.git`
- 源模块路径：`framework/`、`config/`、`workflow/`、`stability/`、`batch_processing/`
- 迁移原则：参考 `exercises-java-core` 的重构方式，按主题重新组织包结构
- 包名格式：`manfred.exercises.framework.<topic>`

## 包内分层规范

```
xxx/              ← Demo 类（有 main 方法，演示入口）
xxx.model/        ← 模型/数据类
xxx.config/       ← 配置类
xxx.service/      ← 服务类
xxx.handler/      ← 处理器类
```

## 代码规范

- 包名格式：`manfred.exercises.framework.<domain>.<topic>`
- 演示类命名：`XxxDemo`（有 main 方法）
- 不在 `src/main/java` 中使用 `@Test` 注解
- 所有 public 类必须有中文类级 Javadoc
- 无 `@author`/`@date` 等元数据注释
- 子模块 pom 不写 `<version>`，版本统一在根 pom 管理

## 构建命令

```bash
mvn clean compile -Dsort.skip=true
mvn clean test -Dsort.skip=true
mvn clean compile -pl spring -Dsort.skip=true
```

## 子模块 pom.xml 模板

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
</project>
```
