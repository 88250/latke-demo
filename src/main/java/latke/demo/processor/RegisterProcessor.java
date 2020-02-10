package latke.demo.processor;

import latke.demo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.http.Request;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.ioc.Singleton;
import org.json.JSONObject;

import java.util.Map;

/**
 * Register.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Feb 10, 2020
 * @since 2.0.0
 */
@Singleton
public class RegisterProcessor {

    @Inject
    private UserService userService;

    public void showRegister(final RequestContext context) {
        context.setRenderer(new SimpleFMRenderer("register.ftl"));
    }

    public void register(final RequestContext context) { // 函数式路由，在 Server 中配置
        context.setRenderer(new SimpleFMRenderer("register.ftl"));
        final Map<String, Object> dataModel = context.getRenderer().getRenderDataModel();

        final Request request = context.getRequest();
        final String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            dataModel.put("name", name);

            userService.saveUser(name, 3);
        }
    }

    public void paraPathVar(final RequestContext context) {
        final String paraVar = context.param("paraVar");
        final String pathVar = context.pathVar("pathVar");
        context.renderJSON(new JSONObject().put("paraVar", paraVar).put("pathVar", pathVar));
    }
}
