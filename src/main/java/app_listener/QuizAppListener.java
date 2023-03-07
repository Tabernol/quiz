package app_listener;

import command.post.FinishTest;
import connection.MyDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.Locale;

@WebListener
public class QuizAppListener implements ServletContextListener {
    private final Logger LOGGER = LogManager.getLogger(QuizAppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext servletContext = sce.getServletContext();
//        Locale locale = new Locale("en","UK");
//        servletContext.setAttribute("locale", locale);
        LOGGER.info("Webapp 'Quiz Service' was started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MyDataSource.closePool();
        LOGGER.info("Webapp 'Quiz service' was closed");
    }


}
