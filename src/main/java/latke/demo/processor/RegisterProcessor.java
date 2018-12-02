package latke.demo.processor;

import latke.demo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.AbstractFreeMarkerRenderer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Register.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.1.0.0, Dec 2, 2018
 * @since 2.0.0
 */
@RequestProcessor
public class RegisterProcessor {

    @Inject
    private UserService userService;

    @RequestProcessing(value = "/register")
    public void showRegister(final HTTPRequestContext context) {
        final AbstractFreeMarkerRenderer render = new SimpleFMRenderer();
        context.setRenderer(render);
        render.setTemplateName("register.ftl");
    }

    public void register(final HTTPRequestContext context) {
        final AbstractFreeMarkerRenderer render = new SimpleFMRenderer();
        context.setRenderer(render);
        render.setTemplateName("register.ftl");
        final Map<String, Object> dataModel = render.getDataModel();

        final HttpServletRequest request = context.getRequest();
        final String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            dataModel.put("name", name);

            userService.saveUser(name, 3);
        }
    }
}
