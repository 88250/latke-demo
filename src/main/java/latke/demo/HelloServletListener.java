package latke.demo;

import org.b3log.latke.Latkes;
import latke.demo.processor.RegisterProcessor;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.servlet.AbstractServletListener;
import org.b3log.latke.servlet.DispatcherServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * Hello servlet listener.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.2, Sep 30, 2018
 * @since 2.0.0
 */
public class HelloServletListener extends AbstractServletListener {

    private static final Logger LOGGER = Logger.getLogger(HelloServletListener.class);

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        Latkes.setScanPath(HelloServletListener.class.getPackage().getName());
        super.contextInitialized(servletContextEvent);

        final BeanManager beanManager= BeanManager.getInstance();
        final RegisterProcessor registerProcessor = beanManager.getReference(RegisterProcessor.class);
        DispatcherServlet.post("/register", registerProcessor::register);
        DispatcherServlet.mapping();

        LOGGER.info("Initialized the context");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        super.contextDestroyed(servletContextEvent);

        LOGGER.info("Destroyed the context");
    }

    @Override
    public void sessionCreated(final HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent httpSessionEvent) {
        super.sessionDestroyed(httpSessionEvent);
    }

    @Override
    public void requestInitialized(final ServletRequestEvent servletRequestEvent) {
    }

    @Override
    public void requestDestroyed(final ServletRequestEvent servletRequestEvent) {
        super.requestDestroyed(servletRequestEvent);
    }
}
