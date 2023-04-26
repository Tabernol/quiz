package app_listener;


import context.AppContext;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;


/**
 * The QuizAppListener class implements the ServletContextListener interface to listen
 * for application-level events and perform certain actions when those events occur.
 * This listener is responsible for logging messages when the webapp
 * is started and stopped.
 */
@WebListener
@Slf4j
public class QuizAppListener implements ServletContextListener {


    /**
     * This method is called when the webapp is initialized.
     * It logs a message indicating that the webapp has started.
     *
     * @param sce the ServletContextEvent that triggered this initialization
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       AppContext.getInstance();
        log.info("Webapp 'Quiz Service' has started.");
    }
}
