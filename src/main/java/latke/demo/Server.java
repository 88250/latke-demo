package latke.demo;

import latke.demo.processor.RegisterProcessor;
import org.b3log.latke.Latkes;
import org.b3log.latke.http.BaseServer;
import org.b3log.latke.http.Dispatcher;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.repository.jdbc.util.JdbcRepositories;

/**
 * Server.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Nov 3, 2019
 */
public class Server extends BaseServer {

    public static void main(String[] args) {
        Latkes.setScanPath(Server.class.getPackage().getName());
        Latkes.init();

        final Server server = new Server();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdown();
            Latkes.shutdown();
        }));

        final BeanManager beanManager = BeanManager.getInstance();
        final RegisterProcessor registerProcessor = beanManager.getReference(RegisterProcessor.class);
        // 附加一个使用函数式路由的示例
        Dispatcher.post("/register", registerProcessor::register);
        Dispatcher.mapping();

        // 初始化数据库表
        JdbcRepositories.initAllTables();

        server.start(8080);
    }
}
