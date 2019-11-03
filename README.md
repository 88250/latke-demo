# latke-demo

[Latke](https://github.com/b3log/latke) 最小化示例，请结合文章 [Latke 快速上手指南](https://hacpai.com/article/1466870492857)进行研究。

## 运行

将项目导入 IDE，通过 `mvn install` 编译后有两种方式启动：

1. 通过 Maven Jetty 插件：`mvn jetty:run`
2. 直接运行 Starter：
   * Windows: `java -cp "target/latke-demo/WEB-INF/lib/*;target/latke-demo/WEB-INF/classes" latke.demo.Server`
   * Linux: `java -cp "target/latke-demo/WEB-INF/lib/*:target/latke-demo/WEB-INF/classes" latke.demo.Server`

打开浏览器访问 `http://localhost:8080`。

## 数据库

本示例使用了内嵌的 H2 作为数据库，数据库文件默认路径为 `~/latke-h2-demo/db`，如有需要请在 local.properties 中进行修改。

## 示例

### 模板页面渲染

* 路径：/
* 功能：渲染（输出 HTML）一个简单的 FreeMarker 页面模板
* 代码：HelloProcessor.java 中 index 方法

### 表单处理

* 路径：/greeting
* 功能：渲染简单的表单以及处理表单提交
* 代码：HelloProcessor.java 中 greeting 方法

### ORM 持久化

* 路径：/register
* 功能：模拟用户注册并持久化数据
* 代码：RegisterProcessor.java 中 showRegister 以及 register 方法

### 路径变量读取

* 路径：/var/{pathVar}
* 功能：读取路径变量
* 代码：RegisterProcessorg.java 中 paraPathVar 方法

## 实际案例

* [Solo](https://github.com/b3log/solo)：一款小而美的 Java 开源博客系统，专为程序员设计
* [Symphony](https://github.com/b3log/symphony)：一款用 Java 实现的现代化社区（论坛/BBS/社交网络/博客）平台
