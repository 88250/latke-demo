package org.b3log.latke.demo.processor;

import org.b3log.latke.demo.service.UserService;
import org.b3log.latke.ioc.inject.Inject;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.freemarker.AbstractFreeMarkerRenderer;
import org.b3log.latke.servlet.renderer.freemarker.FreeMarkerRenderer;
import org.b3log.latke.util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Register.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.2, Apr 5, 2018
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
