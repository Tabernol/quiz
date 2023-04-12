package app_listener;


import connection.MyDataSource;
import controllers.AppContext;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;


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

    /**
     * This method is called when the webapp is destroyed.
     * It closes the connection pool and logs a message indicating
     * that the webapp has been closed.
     *
     * @param sce the ServletContextEvent that triggered this destruction
     */
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        MyDataSource.closePool();
//        log.info("Webapp 'Quiz service' was closed");
//    }
}
