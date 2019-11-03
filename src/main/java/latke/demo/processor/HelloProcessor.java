package latke.demo.processor;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.http.HttpMethod;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.http.annotation.RequestProcessing;
import org.b3log.latke.http.annotation.RequestProcessor;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.util.Requests;

import java.util.Date;
import java.util.Map;

/**
 * Hello.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Nov 3, 2019
 * @since 2.0.0
 */
@RequestProcessor
public class HelloProcessor {

    private static final Logger LOGGER = Logger.getLogger(HelloProcessor.class);

    @RequestProcessing("/")
    public void index(final RequestContext context) {
        context.setRenderer(new SimpleFMRenderer("index.ftl"));

        final Map<String, Object> dataModel = context.getRenderer().getRenderDataModel();
        dataModel.put("greeting", "Hello, Latke!");

        Requests.log(context.getRequest(), Level.DEBUG, LOGGER);
    }

    @RequestProcessing(value = "/greeting", method = {HttpMethod.GET, HttpMethod.POST})
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
