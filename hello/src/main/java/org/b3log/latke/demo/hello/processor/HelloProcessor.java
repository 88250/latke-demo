package org.b3log.latke.demo.hello.processor;

import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.freemarker.AbstractFreeMarkerRenderer;
import org.b3log.latke.servlet.renderer.freemarker.FreeMarkerRenderer;
import org.b3log.latke.util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Hello.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.5, Jul 6, 2016
 */
@RequestProcessor
public class HelloProcessor {

    @RequestProcessing(value = {"/", "/index", "/index.*", "/**/ant/*/path"}, method = HTTPRequestMethod.GET)
    public void index(final HTTPRequestContext context) {
        final AbstractFreeMarkerRenderer render = new FreeMarkerRenderer();
        context.setRenderer(render);

        render.setTemplateName("index.ftl");
        final Map<String, Object> dataModel = render.getDataModel();

        dataModel.put("greeting", "Hello, Latke!");
    }

    @RequestProcessing(value = "/greeting", method = {HTTPRequestMethod.GET, HTTPRequestMethod.POST})
    public void greeting(final HTTPRequestContext context, final HttpServletRequest request) {
        final AbstractFreeMarkerRenderer render = new FreeMarkerRenderer();
        context.setRenderer(render);

        render.setTemplateName("hello.ftl");
        final Map<String, Object> dataModel = render.getDataModel();

        dataModel.put("time", new Date());

        final String name = request.getParameter("name");
        if (!Strings.isEmptyOrNull(name)) {
            dataModel.put("name", name);
        }
    }
}
