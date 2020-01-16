package latke.demo.processor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.http.renderer.AbstractFreeMarkerRenderer;

import java.util.TimeZone;

/**
 * Simple FreeMarker template renderer.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Nov 3, 2019
 * @since 2.0.0
 */
public final class SimpleFMRenderer extends AbstractFreeMarkerRenderer {

    private static final Logger LOGGER = LogManager.getLogger(SimpleFMRenderer.class);

    /**
     * FreeMarker template configuration.
     */
    public static final Configuration SKIN;

    static {
        SKIN = new Configuration(Configuration.VERSION_2_3_29);
        SKIN.setDefaultEncoding("UTF-8");
        SKIN.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SKIN.setClassForTemplateLoading(SimpleFMRenderer.class, "/template");
        SKIN.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        SKIN.setLogTemplateExceptions(false);
    }

    /**
     * Constructs a simple FreeMarker template with the specified template name.
     *
     * @param templateName the specified template name
     */
    public SimpleFMRenderer(final String templateName) {
        setTemplateName(templateName);
    }

    protected void beforeRender(RequestContext context) {
    }

    protected void afterRender(RequestContext context) {
    }

    protected Template getTemplate() {
        try {
            return SKIN.getTemplate(getTemplateName());
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Get template [" + getTemplateName() + "] failed", e);

            return null;
        }
    }
}
