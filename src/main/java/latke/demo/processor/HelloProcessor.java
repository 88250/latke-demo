package latke.demo.processor;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.ioc.Singleton;
import org.b3log.latke.util.Requests;

import java.util.Date;
import java.util.Map;

/**
 * Hello.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Feb 10, 2020
 * @since 2.0.0
 */
@Singleton
public class HelloProcessor {

    private static final Logger LOGGER = LogManager.getLogger(HelloProcessor.class);

    public void index(final RequestContext context) {
        context.setRenderer(new SimpleFMRenderer("index.ftl"));

        final Map<String, Object> dataModel = context.getRenderer().getRenderDataModel();
        dataModel.put("greeting", "Hello, Latke!");

        Requests.log(context.getRequest(), Level.DEBUG, LOGGER);
    }

    public void greeting(final RequestContext context) {
        context.setRenderer(new SimpleFMRenderer("hello.ftl"));

        final Map<String, Object> dataModel = context.getRenderer().getRenderDataModel();
        dataModel.put("time", new Date());
        final String name = context.param("name");
        if (StringUtils.isNotBlank(name)) {
            dataModel.put("name", name);
        }
    }
}
