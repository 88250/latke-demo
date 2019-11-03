package latke.demo;

import latke.demo.processor.RegisterProcessor;
import org.b3log.latke.Latkes;
import org.b3log.latke.http.BaseServer;
import org.b3log.latke.http.Dispatcher;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.repository.jdbc.util.JdbcRepositories;

/**
 * Starter with embedded Jetty, <a href="https://github.com/b3log/solo/issues/12037">standalone mode</a>.
 *
 * <ul>
 * <li>Windows: java -cp "WEB-INF/lib/*;WEB-INF/classes" latke.demo.Server</li>
 * <li>Unix-like: java -cp "WEB-INF/lib/*:WEB-INF/classes" latke.demo.Server</li>
 * </ul>
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Nov 3, 2019
 */
public class Server extends BaseServer {

    public static void main(String[] args) {
        Latkes.setScanPath(Server.class.getPackage().getName());

        final BeanManager beanManager = BeanManager.getInstance();
        final RegisterProcessor registerProcessor = beanManager.getReference(RegisterProcessor.class);
        // 附加一个使用函数式路由的示例
        Dispatcher.post("/register", registerProcessor::register);
        Dispatcher.mapping();

        // 初始化数据库表
        JdbcRepositories.initAllTables();

        final Server server = new Server();
        server.start(8080);
    }
}
