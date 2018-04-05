# latke-demo

[Latke](https://github.com/b3log/latke) 最小化示例，请结合文章 [Latke 快速上手指南](https://hacpai.com/article/1466870492857)进行研究。

## 运行

将项目导入 IDE，通过 `mvn install` 编译后有两种方式启动：

1. `mvn jetty:run`
2. 运行 Starter 

打开浏览器访问 `http://localhost:8080`。

## 数据库

本示例使用了内嵌的 H2 作为数据库，数据库文件默认路径为 `~/latke-h2-demo/db`，如有需要请在 local.properties 中进行修改。


