# WQS Admin System

基于 [Solon](https://solon.noear.org/) 框架构建的轻量级后台管理系统后端。

## 🚀 项目简介

WQS Admin 是一个现代化的 Java 后端项目，旨在提供简洁、高效的开发体验。它采用模块化设计，集成了 MyBatis Plus、Sa-Token、Redis 以及国密算法支持。

## 🛠️ 技术栈

- **核心框架**: [Solon](https://solon.noear.org/) (更小、更快、更自由的 Java 框架)
- **ORM**: MyBatis Plus + Solon Plugin
- **鉴权**: [Sa-Token](https://sa-token.cc/)
- **缓存**: Redis (Jedis)
- **工具**: Hutool, Lombok, Fastjson2
- **加密**: 国密 SM2/SM3/SM4 支持

## 📂 模块说明

- **wqs-admin-parent**: 父工程，管理依赖版本。
- **wqs-core**: 核心模块，包含通用工具类、基类实体、统一返回结果、异常定义等。
- **wqs-system**: 系统业务模块，包含用户管理、权限认证等业务逻辑。
- **wqs-start**: 启动模块，包含应用入口、全局配置、资源文件。

## ⚙️ 快速开始

### 1. 环境准备
- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis

### 2. 配置数据库与 Redis
修改 `wqs-start/src/main/resources/app.yml` 文件：

```yaml
wqs:
  db:
    url: "jdbc:mysql://localhost:3306/wqs_admin?..."
    username: "root"
    password: "your_password"
  redis:
    server: "127.0.0.1:6379"
```

### 3. 编译运行

由于是多模块项目，首次运行建议在根目录执行安装：

```bash
mvn clean install -DskipTests
```

然后运行启动类：
`wqs-start/src/main/java/com/wqs/admin/App.java`

或者使用 Maven 运行：

```bash
mvn clean compile exec:java -pl wqs-start -am -Dexec.mainClass="com.wqs.admin.App"
```

## ✨ 主要特性

- **轻量级**: 基于 Solon，启动快，内存占用低。
- **统一结果**: 标准化的 `Result<T>` 返回结构。
- **安全认证**: 集成 Sa-Token，轻松实现登录、鉴权。
- **国密支持**: 内置 `SmCryptoUtil`，支持 SM2/SM3/SM4 加解密。
- **多数据源**: 支持配置多个数据源（当前已配置 `db1`）。

## 📝 常用接口

- `GET /`: 欢迎页
- `GET /hello`: Hello World 示例
- `POST /auth/login`: 登录 (测试账号: admin / 123456)
- `GET /auth/info`: 获取当前登录用户信息
- `GET /auth/testSm`: 国密算法测试

## 📄 开源协议

MIT License
