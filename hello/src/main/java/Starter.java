/*
 * Copyright (c) 2010-2016, b3log.org & hacpai.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.b3log.latke.Latkes;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 * Solo with embedded Jetty, <a href="https://github.com/b3log/solo/issues/12037">standalone mode</a>.
 *
 * <ul>
 * <li>Windows: java -cp WEB-INF/lib/*;WEB-INF/classes org.b3log.solo.Starter</li>
 * <li>Unix-like: java -cp WEB-INF/lib/*:WEB-INF/classes org.b3log.solo.Starter</li>
 * </ul>
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.1.0.7, Dec 23, 2015
 * @since 1.2.0
 */
public class Starter {


    public static void main(String [] args) throws Exception {
        Latkes.setScanPath("org.b3log.latke.demo.hello"); // For Latke IoC
        Latkes.initRuntimeEnv();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getFile();
        path = path.replace("target/classes/", "");
        path = path.replace("classes/", "");
        String web = path + "src/main/webapp/";
        final Server server = new Server(8080);
        final WebAppContext root = new WebAppContext();
        root.setParentLoaderPriority(true);
        root.setContextPath("/");
        root.setResourceBase(web);
        root.setDescriptor(web + "/WEB-INF/web.xml");
        server.setHandler(root);
        server.start();
        server.join();
    }
}

