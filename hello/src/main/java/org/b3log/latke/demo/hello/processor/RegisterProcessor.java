package org.b3log.latke.demo.hello.processor;

import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.b3log.latke.demo.hello.service.UserService;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.freemarker.AbstractFreeMarkerRenderer;
import org.b3log.latke.servlet.renderer.freemarker.FreeMarkerRenderer;
import org.b3log.latke.util.Strings;

/**
 * Register.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.1, Oct 9, 2012
 */
@RequestProcessor
public class RegisterProcessor {

    @Inject
    private UserService userService;

    @RequestProcessing(value = "/register", method = {HTTPRequestMethod.GET, HTTPRequestMethod.POST})
    public void register(final HTTPRequestContext context, final HttpServletRequest request) {
        final AbstractFreeMarkerRenderer render = new FreeMarkerRenderer();
        context.setRenderer(render);

        render.setTemplateName("register.ftl");
        final Map<String, Object> dataModel = render.getDataModel();

        final String name = request.getParameter("name");
        if (!Strings.isEmptyOrNull(name)) {
            dataModel.put("name", name);

            userService.saveUser(name, 3);
        }
    }
}
