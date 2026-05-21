# exercises-framework

> A hands-on collection of Java framework and middleware exercises — Spring Core, Spring Boot, Spring Cloud, Kafka, and more.

[![Java](https://img.shields.io/badge/Java-1.8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

[中文文档](README_CN.md)

---

## Overview

`exercises-framework` is a self-contained multi-module Maven project for practicing and demonstrating commonly used Java frameworks and middleware. Each module is independently runnable and focuses on a specific technology, making it easy to learn by reading and running the demo entry classes.

This project was migrated and refactored from [manfredma/exercises](https://github.com/manfredma/exercises).  
Reference structure: [manfredma/exercises-java-core](https://github.com/manfredma/exercises-java-core).

---

## Modules

| Module | Technology | Description |
|--------|-----------|-------------|
| `spring-core` | Spring Framework | IoC, AOP (AspectJ / CGLIB / JDK proxy), JdbcTemplate, declarative transactions, custom XML tags |
| `spring-boot` | Spring Boot 2.7 | Auto-configuration, property binding, H2 JDBC, embedded Tomcat/Jetty, custom Starter |
| `spring-cloud` | Spring Cloud 2021 | Circuit breaker (Hystrix + Resilience4j), retry |
| `messaging-kafka` | Apache Kafka 3.4 | Producer, consumer, topic management |
| `stability` | Hystrix / Sentinel / Resilience4j | Circuit breaker, rate limiting, retry patterns |
| `workflow` | EasyFlows / EasyRules / EasyStates | Rule engine, state machine, workflow orchestration |
| `config` | Apollo / Archaius | Dynamic configuration center integration |
| `spring-batch` | Spring Batch 4.3 | Batch job, step, ItemReader / ItemWriter / ItemProcessor |
| `event-sourcing` | Axon Framework 4.9 | Event sourcing, CQRS, aggregate |
| `ioc-guice` | Google Guice 5.1 | Dependency injection without Spring |

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java | 1.8+ |
| Maven | 3.6+ |

> **Note:** The project is compiled with Java 1.8 targets. If you run the demos from an IDE with JDK 11+, use `@Autowired` for injection (already applied in all modules) to avoid `javax.annotation` module path issues.

---

## Getting Started

### Clone

```bash
git clone https://github.com/manfredma/exercises-framework.git
cd exercises-framework
```

### Build all modules

```bash
mvn clean compile -Dsort.skip=true
```

### Run a demo

Each module has one or more entry classes with a `main()` method. For example:

```bash
# Spring Core — JdbcTemplate CRUD demo
mvn exec:java -pl spring-core -Dexec.mainClass="manfred.exercises.framework.spring.core.jdbc.JdbcTemplateDemo" -Dsort.skip=true

# Spring Core — Transaction commit/rollback demo
mvn exec:java -pl spring-core -Dexec.mainClass="manfred.exercises.framework.spring.core.jdbc.TransactionDemo" -Dsort.skip=true

# Spring Boot — Property binding demo (exits automatically)
mvn exec:java -pl spring-boot -Dexec.mainClass="manfred.exercises.framework.spring.boot.config.PropertyBindingApp" -Dsort.skip=true
```

### Run tests

```bash
# All modules
mvn clean test -Dsort.skip=true

# Single module
mvn clean test -pl spring-boot -Dsort.skip=true
```

---

## Project Structure

```
exercises-framework/
├── pom.xml                  ← Root POM, Spring Boot 2.7.18 BOM
├── spring-core/             ← Spring Core demos
│   └── src/main/java/
│       └── .../spring/core/
│           ├── bean/        ← BeanFactoryDemo, BeanWrapperDemo (entry)
│           │   └── circular/   ← CircularDepBeanA/B (model)
│           ├── customtag/   ← SpringCustomTagDemo (entry)
│           │   └── impl/       ← Car, parsers, handlers
│           ├── proxy/       ← 6 proxy demos (entry)
│           │   ├── aspectj/    ← AOP annotation, aspect, service
│           │   ├── cglib/      ← CGLIB ProxyFactory
│           │   ├── dao/        ← IUserDao, UserDao
│           │   ├── hardcoded/  ← UserDaoProxy
│           │   ├── interceptor/← TimeCostInterceptor
│           │   └── jdk/        ← JDK ProxyFactory
│           └── jdbc/        ← JdbcTemplateDemo, TransactionDemo (entry)
│               ├── config/     ← JdbcConfig
│               ├── model/      ← Account
│               ├── repository/ ← AccountRepository
│               └── service/    ← TransferService
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

## Package Naming Convention

All source packages follow the pattern:

```
manfred.exercises.framework.<domain>.<topic>
```

| Module | Base Package |
|--------|-------------|
| spring-core | `manfred.exercises.framework.spring.core` |
| spring-boot | `manfred.exercises.framework.spring.boot` |
| spring-cloud | `manfred.exercises.framework.spring.cloud` |
| messaging-kafka | `manfred.exercises.framework.messaging.kafka` |
| stability | `manfred.exercises.framework.stability` |

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/my-new-exercise`
3. Commit your changes following [Conventional Commits](https://www.conventionalcommits.org/)
4. Push and open a Pull Request

---

## License

This project is licensed under the [MIT License](LICENSE).
