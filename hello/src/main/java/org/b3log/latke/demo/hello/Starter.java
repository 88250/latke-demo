package org.b3log.latke.demo.hello;

import java.io.File;
import org.b3log.latke.Latkes;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Starter with embedded Jetty, <a href="https://github.com/b3log/solo/issues/12037">standalone mode</a>.
 *
 * <ul>
 * <li>Windows: java -cp WEB-INF/lib/*;WEB-INF/classes org.b3log.latke.demo.hello.Starter</li>
 * <li>Unix-like: java -cp WEB-INF/lib/*:WEB-INF/classes org.b3log.latke.demo.hello.Starter</li>
 * </ul>
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @author <a href="https://applehater.cn">zonghua</a>
 * @version 1.0.1.1, Sept 22, 2016
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        Latkes.setScanPath("org.b3log.latke.demo.hello"); // For Latke IoC
        Latkes.initRuntimeEnv();

        String classesPath = ClassLoader.getSystemResource("").getPath(); // Real path including maven sub folder
        String webappDirLocation = classesPath.replace("target/classes/","src/main/webapp/"); // POM structure in dev env
        final File file = new File(webappDirLocation);
        if (!file.exists()) {
            webappDirLocation = "."; // production environment
        }

        final Server server = new Server(Integer.valueOf(Latkes.getServerPort()));
        final WebAppContext root = new WebAppContext();
        root.setParentLoaderPriority(true); // Use parent class loader
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        server.setHandler(root);

        try {
            server.start();
        } catch (final Exception e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }
}
