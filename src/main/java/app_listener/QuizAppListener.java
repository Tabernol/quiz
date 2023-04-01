package app_listener;

import command.post.FinishTest;
import connection.MyDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.Locale;

/**
 * The QuizAppListener class implements the ServletContextListener interface to listen
 * for application-level events and perform certain actions when those events occur.
 * This listener is responsible for logging messages when the webapp
 * is started and stopped.
 */
@WebListener
public class QuizAppListener implements ServletContextListener {
    private final Logger LOGGER = LogManager.getLogger(QuizAppListener.class);

    /**
     * This method is called when the webapp is initialized.
     * It logs a message indicating that the webapp has started.
     *
     * @param sce the ServletContextEvent that triggered this initialization
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Webapp 'Quiz Service' has started.");
    }

    /**
     * This method is called when the webapp is destroyed.
     * It closes the connection pool and logs a message indicating
     * that the webapp has been closed.
     *
     * @param sce the ServletContextEvent that triggered this destruction
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MyDataSource.closePool();
        LOGGER.info("Webapp 'Quiz service' was closed");
    }
}
