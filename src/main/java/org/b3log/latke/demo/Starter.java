package org.b3log.latke.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Starter with embedded Jetty, <a href="https://github.com/b3log/solo/issues/12037">standalone mode</a>.
 *
 * <ul>
 * <li>Windows: java -cp "WEB-INF/lib/*;WEB-INF/classes" Starter</li>
 * <li>Unix-like: java -cp "WEB-INF/lib/*:WEB-INF/classes" Starter</li>
 * </ul>
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @author <a href="https://applehater.cn">zonghua</a>
 * @version 1.0.1.13, Oct 23, 2018
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        String classesPath = ClassLoader.getSystemResource("").getPath(); // Real path including maven sub folder
        String webappDirLocation = classesPath.replace("target/classes/", "src/main/webapp/"); // POM structure in dev env
        final File file = new File(webappDirLocation);
        if (!file.exists()) {
            webappDirLocation = "."; // production environment
        }

        final Server server = new Server(8080);
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
